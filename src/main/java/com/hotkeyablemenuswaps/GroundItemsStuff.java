/*
 * Copyright (c) 2017, Aria <aria@ar1as.space>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
 * Copyright (c) 2020, dekvall <https://github.com/dekvall>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.hotkeyablemenuswaps;

import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.inject.Inject;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.GameState;
import net.runelite.api.ItemComposition;
import net.runelite.api.ItemID;
import net.runelite.api.Tile;
import net.runelite.api.TileItem;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemDespawned;
import net.runelite.api.events.ItemQuantityChanged;
import net.runelite.api.events.ItemSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.util.GameEventManager;
import net.runelite.client.util.Text;
import net.runelite.client.util.WildcardMatcher;

// Mostly copypasted stuff from runelite's ground items plugin. Used to access highlighted and hidden item lists.
@Slf4j
public class GroundItemsStuff
{
	@Inject private ItemManager itemManager;
	@Inject private ConfigManager configManager;
	@Inject private GameEventManager gameEventManager;
	@Inject private EventBus eventBus;

	public final Table<WorldPoint, Integer, GroundItem> collectedGroundItems = HashBasedTable.create();
	public LoadingCache<NamedQuantity, Boolean> highlightedItems;
	public LoadingCache<NamedQuantity, Boolean> hiddenItems;

	@Subscribe
	public void onGameStateChanged(final GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOADING)
		{
			// log.debug("[GIS-onGameStateChanged] Clearing collected ground items");
			collectedGroundItems.clear();
		}
	}

	@Subscribe
	public void onItemSpawned(ItemSpawned itemSpawned)
	{
		TileItem item = itemSpawned.getItem();
		Tile tile = itemSpawned.getTile();

		GroundItem groundItem = buildGroundItem(tile, item);
		GroundItem existing = collectedGroundItems.get(tile.getWorldLocation(), item.getId());
		if (existing != null)
		{
			existing.setQuantity(existing.getQuantity() + groundItem.getQuantity());
			// The spawn time remains set at the oldest spawn
			// log.debug("[GIS-onItemSpawned] Updated ground item in collectedGroundItems: {}", groundItem.getName());
		}
		else
		{
			collectedGroundItems.put(tile.getWorldLocation(), item.getId(), groundItem);
			// log.debug("[GIS-onItemSpawned] Added ground item to collectedGroundItems: {}", groundItem.getName());
		}
	}

	@Subscribe
	public void onItemDespawned(ItemDespawned itemDespawned)
	{
		TileItem item = itemDespawned.getItem();
		Tile tile = itemDespawned.getTile();

		GroundItem groundItem = collectedGroundItems.get(tile.getWorldLocation(), item.getId());
		if (groundItem == null)
		{
			// log.debug("[GIS-onItemDespawned] Ground item despawned, but not found in collectedGroundItems: {}", itemManager.getItemComposition(item.getId()).getName());
			return;
		}

		if (groundItem.getQuantity() <= item.getQuantity())
		{
			// log.debug("[GIS-onItemDespawned] Removed ground item from collectedGroundItems: {}", groundItem.getName());
			collectedGroundItems.remove(tile.getWorldLocation(), item.getId());
		}
		else
		{
			groundItem.setQuantity(groundItem.getQuantity() - item.getQuantity());
			// When picking up an item when multiple stacks appear on the ground,
			// it is not known which item is picked up, so we invalidate the spawn
			// time
			groundItem.setSpawnTime(null);
			// log.debug("[GIS-onItemDespawned] Updated ground item in collectedGroundItems: {} from {}->{]", groundItem.getName(), groundItem.getQuantity() + item.getQuantity(), groundItem.getQuantity());
		}
	}

	@Subscribe
	public void onItemQuantityChanged(ItemQuantityChanged itemQuantityChanged)
	{
		TileItem item = itemQuantityChanged.getItem();
		Tile tile = itemQuantityChanged.getTile();
		int oldQuantity = itemQuantityChanged.getOldQuantity();
		int newQuantity = itemQuantityChanged.getNewQuantity();

		int diff = newQuantity - oldQuantity;
		GroundItem groundItem = collectedGroundItems.get(tile.getWorldLocation(), item.getId());
		if (groundItem != null)
		{
			// log.debug("[GIS-onItemQuantityChanged] Ground item quantity changed: {} from {}->{}", groundItem.getName(), oldQuantity, newQuantity);
			groundItem.setQuantity(groundItem.getQuantity() + diff);
		}
	}

	@Data
	@Builder
	static class GroundItem
	{
		private int id;
		private int itemId;
		private String name;
		private int quantity;
		private WorldPoint location;
		private int height;
		private int haPrice;
		private int gePrice;
		private int offset;
		private boolean tradeable;
		@Nullable
		private Instant spawnTime;
		private boolean stackable;

		int getHaPrice()
		{
			if (isStackable()) {
				return haPrice * quantity;
			} else {
				return haPrice;
			}
		}

		int getGePrice()
		{
			if (isStackable()) {
				return gePrice * quantity;
			} else {
				return gePrice;
			}
		}
	}

	private GroundItem buildGroundItem(final Tile tile, final TileItem item)
	{
		// Collect the data for the item
		final int itemId = item.getId();
		final ItemComposition itemComposition = itemManager.getItemComposition(itemId);
		final int realItemId = itemComposition.getNote() != -1 ? itemComposition.getLinkedNoteId() : itemId;
		final int alchPrice = itemComposition.getHaPrice();

		final GroundItem groundItem = GroundItem.builder()
			.id(itemId)
			.location(tile.getWorldLocation())
			.itemId(realItemId)
			.quantity(item.getQuantity())
			.name(itemComposition.getName())
			.haPrice(alchPrice)
			.height(tile.getItemLayer().getHeight())
			.tradeable(itemComposition.isTradeable())
			.spawnTime(Instant.now())
			.stackable(itemComposition.isStackable())
			.build();

		switch(realItemId)
		{
			case ItemID.COINS_995:
				groundItem.setGePrice(1);
				groundItem.setHaPrice(1);
				break;
			case ItemID.PLATINUM_TOKEN:
				groundItem.setGePrice(1000);
				groundItem.setHaPrice(1000);
				break;
			default:
				groundItem.setGePrice(itemManager.getItemPrice(realItemId));
				break;
		}

		// log.debug("Built new ground item {}", groundItem);
		return groundItem;
	}

	@Value
	@RequiredArgsConstructor
	public static class NamedQuantity
	{
		private final String name;
		private final int quantity;

		NamedQuantity(GroundItem groundItem)
		{
			this(groundItem.getName(), groundItem.getQuantity());
		}
	}

	class WildcardMatchLoader extends CacheLoader<NamedQuantity, Boolean>
	{
		private final List<ItemThreshold> itemThresholds;

		WildcardMatchLoader(List<String> configEntries)
		{
			this.itemThresholds = configEntries.stream()
				.map(ItemThreshold::fromConfigEntry)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		}

		@Override
		public Boolean load(@Nonnull final NamedQuantity key)
		{
			if (Strings.isNullOrEmpty(key.getName()))
			{
				return false;
			}

			final String filteredName = key.getName().trim();

			for (final ItemThreshold entry : itemThresholds)
			{
				if (WildcardMatcher.matches(entry.getItemName(), filteredName)
					&& entry.quantityHolds(key.getQuantity()))
				{
					return true;
				}
			}

			return false;
		}
	}

	@Value
	static class ItemThreshold
	{
		enum Inequality
		{
			LESS_THAN,
			MORE_THAN
		}

		private final String itemName;
		private final int quantity;
		private final ItemThreshold.Inequality inequality;

		static ItemThreshold fromConfigEntry(String entry)
		{
			if (Strings.isNullOrEmpty(entry))
			{
				return null;
			}

			ItemThreshold.Inequality operator = ItemThreshold.Inequality.MORE_THAN;
			int qty = 0;

			for (int i = entry.length() - 1; i >= 0; i--)
			{
				char c = entry.charAt(i);
				if (c >= '0' && c <= '9' || Character.isWhitespace(c))
				{
					continue;
				}
				switch (c)
				{
					case '<':
						operator = ItemThreshold.Inequality.LESS_THAN;
						// fallthrough
					case '>':
						if (i + 1 < entry.length())
						{
							try
							{
								qty = Integer.parseInt(entry.substring(i + 1).trim());
							}
							catch (NumberFormatException e)
							{
								qty = 0;
								operator = ItemThreshold.Inequality.MORE_THAN;
							}
							entry = entry.substring(0, i);
						}
				}
				break;
			}

			return new ItemThreshold(entry.trim(), qty, operator);
		}

		boolean quantityHolds(int itemCount)
		{
			if (inequality == ItemThreshold.Inequality.LESS_THAN)
			{
				return itemCount < quantity;
			}
			else
			{
				return itemCount > quantity;
			}
		}
	}

	private boolean registered = false;

	public void reloadGroundItemPluginLists(boolean shouldSortByPrice, boolean highlightedList, boolean hiddenList, boolean listsChanged)
	{
		if (highlightedList || hiddenList || shouldSortByPrice) {
			if (!registered) {
				gameEventManager.simulateGameEvents(this);
				eventBus.register(this);
				registered = true;
				log.debug("Registered GroundItemsStuff");
			}
		} else {
			eventBus.unregister(this);
			registered = false;
			collectedGroundItems.clear();
			log.debug("Unregistered GroundItemsStuff");
		}

		if (highlightedList) {
			if (highlightedItems == null || listsChanged) {
				List<String> highlightedItemsList = Text.fromCSV(configManager.getConfiguration("grounditems", "highlightedItems"));
				highlightedItems = CacheBuilder.newBuilder()
					.maximumSize(512L)
					.expireAfterAccess(10, TimeUnit.MINUTES)
					.build(new WildcardMatchLoader(highlightedItemsList));
			}
		} else {
			highlightedItems = null;
		}

		if (hiddenList) {
			if (hiddenItems == null || listsChanged) {
				List<String> hiddenItemsList = Text.fromCSV(configManager.getConfiguration("grounditems", "hiddenItems"));
				hiddenItems = CacheBuilder.newBuilder()
					.maximumSize(512L)
					.expireAfterAccess(10, TimeUnit.MINUTES)
					.build(new WildcardMatchLoader(hiddenItemsList));
			}
		} else {
			hiddenItems = null;
		}
	}
}
