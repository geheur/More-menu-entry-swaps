package com.hotkeyablemenuswaps;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.EnumComposition;
import net.runelite.api.EnumID;
import net.runelite.api.GameState;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemComposition;
import net.runelite.api.ItemID;
import net.runelite.api.Varbits;
import net.runelite.api.WorldType;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;

@Slf4j
public class InventoryManager
{

	@Inject
	private Client client;

	@Inject
	private ItemManager itemManager;

	@Getter
	// <ItemID, Quantity>
	Multiset<Integer> inventoryItems = HashMultiset.create(28);
	@Getter
	// <Slot #, <ItemID, Quantity>>
	Map<Integer, RunePouchEntry> runePouchItems = new HashMap<>();
	BiMap<Integer, Integer> runeIDItemIDBiMap;
	Set<Integer> runePouches = Sets.newHashSet(ItemID.RUNE_POUCH, ItemID.RUNE_POUCH_L, ItemID.DIVINE_RUNE_POUCH, ItemID.DIVINE_RUNE_POUCH_L);

	@Getter
	boolean isRunePickingEnabled;

	@Getter
	@Setter
	private int waitForGameTicks = 2;

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		if (waitForGameTicks > 0 && --waitForGameTicks == 0)
		{
			log.debug("[InventoryManager#onGameTick] Checking to see if we need to initialize InventoryManager state");
			// We already clear the inventory items on ItemContainerChanged, so we don't need to do it here
			this.onItemContainerChanged(new ItemContainerChanged(InventoryID.INVENTORY.getId(), client.getItemContainer(InventoryID.INVENTORY.getId())));
			// Reinitialize the rune pouch varbits
			this.initializeRunePouchVarbits();
			if (runeIDItemIDBiMap == null)
			{
				runeIDItemIDBiMap = createRuneIDItemIDBiMap();
			}
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGING_IN || gameStateChanged.getGameState() == GameState.HOPPING)
		{
			if (runeIDItemIDBiMap == null)
			{
				runeIDItemIDBiMap = createRuneIDItemIDBiMap();
			}
			waitForGameTicks = 2;
		}
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged itemContainerChanged)
	{
		log.debug("[InventoryManager#onItemContainerChanged] Container {} Changed", itemContainerChanged.getContainerId());
		//
		if (itemContainerChanged.getContainerId() == InventoryID.INVENTORY.getId())
		{
			inventoryItems.clear();
		}
		Item[] items = itemContainerChanged.getItemContainer().getItems();
		for (Item item : items)
		{
			if (itemContainerChanged.getContainerId() == InventoryID.INVENTORY.getId() && item.getId() != -1)
			{
				int itemID = item.getId();
				ItemComposition itemComposition = itemManager.getItemComposition(itemID);
				int itemQuantity = itemComposition.isStackable() ? 1 : item.getQuantity();
				inventoryItems.add(item.getId(), itemQuantity);
			}
		}
		log.debug("[InventoryManager#onItemContainerChanged] Inventory: {}", inventoryItems);
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged varbitChanged)
	{
		log.debug("[InventoryManager#onVarbitChanged] Varbit {} Changed: {}", varbitChanged.getVarbitId(), varbitChanged.getValue());

		if (varbitChanged.getVarbitId() == 5698)
		{
			isRunePickingEnabled = varbitChanged.getValue() == 1;
			log.debug("[InventoryManager#onVarbitChanged] Rune Picking Enabled?: {}", isRunePickingEnabled);
		}

		// For the rune pouch, it seems like amount fires before the rune does
		int pouchSlot, runeID, quantity;
		switch (varbitChanged.getVarbitId())
		{
			case Varbits.RUNE_POUCH_RUNE1:
			case Varbits.RUNE_POUCH_AMOUNT1:
				pouchSlot = 1;
				break;
			case Varbits.RUNE_POUCH_RUNE2:
			case Varbits.RUNE_POUCH_AMOUNT2:
				pouchSlot = 2;
				break;
			case Varbits.RUNE_POUCH_RUNE3:
			case Varbits.RUNE_POUCH_AMOUNT3:
				pouchSlot = 3;
				break;
			case Varbits.RUNE_POUCH_RUNE4:
			case Varbits.RUNE_POUCH_AMOUNT4:
				pouchSlot = 4;
				break;
			case Varbits.RUNE_POUCH_RUNE5:
			case Varbits.RUNE_POUCH_AMOUNT5:
				pouchSlot = 5;
				break;
			case Varbits.RUNE_POUCH_RUNE6:
			case Varbits.RUNE_POUCH_AMOUNT6:
				pouchSlot = 6;
				break;
			default:
				return;
		}

		RunePouchEntry runePouchEntry = runePouchItems.getOrDefault(pouchSlot, new RunePouchEntry());
		switch (varbitChanged.getVarbitId())
		{
			case Varbits.RUNE_POUCH_RUNE1:
			case Varbits.RUNE_POUCH_RUNE2:
			case Varbits.RUNE_POUCH_RUNE3:
			case Varbits.RUNE_POUCH_RUNE4:
			case Varbits.RUNE_POUCH_RUNE5:
			case Varbits.RUNE_POUCH_RUNE6:
				runeID = varbitChanged.getValue();
				runePouchEntry.setRuneID(runeID);
				break;
			case Varbits.RUNE_POUCH_AMOUNT1:
			case Varbits.RUNE_POUCH_AMOUNT2:
			case Varbits.RUNE_POUCH_AMOUNT3:
			case Varbits.RUNE_POUCH_AMOUNT4:
			case Varbits.RUNE_POUCH_AMOUNT5:
			case Varbits.RUNE_POUCH_AMOUNT6:
				quantity = varbitChanged.getValue();
				runePouchEntry.setQuantity(quantity);
				break;
		}
		runePouchEntry.setSlotIndex(pouchSlot);
		runePouchEntry.setItemID(getItemIDFromRuneID(runePouchEntry.getRuneID()));
		runePouchItems.put(pouchSlot, runePouchEntry);
		log.debug("[InventoryManager#onVarbitChanged] Rune Pouch: {}", runePouchItems);
	}

	@Data
	class RunePouchEntry
	{
		int slotIndex = 0; // Starting from slot index 1
		int runeID = 0; // OSRS rune pouch uses their own rune ids for the runes (1-22), and 0 is empty
		int itemID = 0; // The actual item ID of the rune
		int quantity = 0; // OSRS defines an empty slot as quantity 0 for the rune pouch
	}

	public int getFreeInventorySlots()
	{
		return 28 - inventoryItems.size();
	}

	public int getUsedInventorySlots()
	{
		return inventoryItems.size();
	}

	public boolean inventoryContains(int itemID)
	{
		return inventoryItems.contains(itemID);
	}

	public boolean inventoryContainsRunePouch()
	{
		return runePouches.stream().anyMatch(this::inventoryContains);
	}

	public int getFreeRunePouchSlots()
	{
		int freeSlots = 6 - runePouchItems.size();
		for (RunePouchEntry entry : runePouchItems.values())
		{
			if (entry.getItemID() == 0)
			{
				freeSlots++;
			}
		}
		return freeSlots;
	}

	public int getUsedRunePouchSlots()
	{
		int usedSlots = 0;
		for (RunePouchEntry entry : runePouchItems.values())
		{
			if (entry.getItemID() != 0)
			{
				usedSlots++;
			}
		}
		return usedSlots;
	}

	public int getRunePouchQuantityAtSlot(int slotIndex)
	{
		if (slotIndex < 1 || slotIndex > 6)
		{
			return 0;
		}

		RunePouchEntry entry = runePouchItems.get(slotIndex);
		return entry == null ? 0 : entry.getQuantity();
	}

	public int getRunePouchRemainingQuantityAtSlot(int slotIndex)
	{
		if (slotIndex < 1 || slotIndex > 6)
		{
			return 0;
		}

		int currentQuantity = getRunePouchQuantityAtSlot(slotIndex);
		return 16000 - currentQuantity;
	}

	public RunePouchEntry getRunePouchEntryAtSlot(int slotIndex)
	{
		if (slotIndex < 1 || slotIndex > 6)
		{
			return null;
		}

		return runePouchItems.get(slotIndex);
	}

	public RunePouchEntry getRunePouchEntryForItemID(int itemID)
	{
		return runePouchItems.values().stream().filter(entry -> entry.getItemID() == itemID).findFirst().orElse(null);
	}

	public RunePouchEntry getRunePouchEntryForRuneID(int runeID)
	{
		return runePouchItems.values().stream().filter(entry -> entry.getRuneID() == runeID).findFirst().orElse(null);
	}

	public boolean canStoreItemInRunePouch(int itemID, int quantity)
	{
		if (!isItemARune(itemID))
		{
			return false;
		}
		RunePouchEntry matchingRunePouchEntry = getRunePouchEntryForItemID(itemID);
		// The rune-picking behavior requires that the rune pouch contains a matching rune, else it won't go into the pouch
		if (matchingRunePouchEntry == null)
		{
			return false;
		}
		else
		{
			return matchingRunePouchEntry.getQuantity() + quantity <= 16000;
		}
	}

	public boolean isItemARune(int itemID)
	{
		return runeIDItemIDBiMap.inverse().containsKey(itemID);
	}

	private BiMap<Integer, Integer> createRuneIDItemIDBiMap()
	{
		// getIntVals starts at index=1 -> Rune pouch rune starts from 1 and goes to n (22 runes)
		EnumComposition ec = client.getEnum(EnumID.RUNEPOUCH_RUNE);
		int[] runeIDs = ec.getKeys();
		int[] itemIDs = ec.getIntVals();
		BiMap<Integer, Integer> runeIDItemIDBiMap = HashBiMap.create(runeIDs.length);
		for (int i = 0; i < runeIDs.length; i++)
		{
			runeIDItemIDBiMap.put(runeIDs[i], itemIDs[i]);
		}
		return runeIDItemIDBiMap;
	}

	public int getItemIDFromRuneID(int runeID)
	{
		return runeIDItemIDBiMap.getOrDefault(runeID, -1);
	}

	public int getRuneIDFromItemID(int itemID)
	{
		return runeIDItemIDBiMap.inverse().getOrDefault(itemID, -1);
	}

	private void initializeRunePouchVarbits() {
		int[] pouchRunes = {Varbits.RUNE_POUCH_RUNE1, Varbits.RUNE_POUCH_RUNE2, Varbits.RUNE_POUCH_RUNE3, Varbits.RUNE_POUCH_RUNE4, Varbits.RUNE_POUCH_RUNE5, Varbits.RUNE_POUCH_RUNE6};
		int[] pouchAmounts = {Varbits.RUNE_POUCH_AMOUNT1, Varbits.RUNE_POUCH_AMOUNT2, Varbits.RUNE_POUCH_AMOUNT3, Varbits.RUNE_POUCH_AMOUNT4, Varbits.RUNE_POUCH_AMOUNT5, Varbits.RUNE_POUCH_AMOUNT6};

		int maxPouchs = this.isPlayerInLeaguesWorld() ? 6 : 4;
		boolean isDivinePouch = this.inventoryContains(ItemID.DIVINE_RUNE_POUCH) || this.inventoryContains(ItemID.DIVINE_RUNE_POUCH_L);
		if (!isDivinePouch) {
			maxPouchs--;
		}

		for (int pouchSlot = 1; pouchSlot <= maxPouchs; pouchSlot++) {
			int varbitRuneID = pouchRunes[pouchSlot - 1];
			int varbitAmount = pouchAmounts[pouchSlot - 1];
			// Rune Type
			VarbitChanged varbitRuneIDChanged = new VarbitChanged();
			varbitRuneIDChanged.setVarbitId(varbitRuneID);
			varbitRuneIDChanged.setValue(client.getVarbitValue(varbitRuneID));
			this.onVarbitChanged(varbitRuneIDChanged);
			// Rune Amount
			VarbitChanged varbitRuneQuantityChanged = new VarbitChanged();
			varbitRuneQuantityChanged.setVarbitId(varbitAmount);
			varbitRuneQuantityChanged.setValue(client.getVarbitValue(varbitAmount));
			this.onVarbitChanged(varbitRuneQuantityChanged);
		}
	}

	// Thanks to the Time Tracking plugin - Also note that Leagues world = SEASONAL so we have to check that it's also not DMM
	private boolean isPlayerInLeaguesWorld() {
		Set<WorldType> worldTypes = Sets.newHashSet(client.getWorldType());
		return worldTypes.contains(WorldType.SEASONAL) && !worldTypes.contains(WorldType.DEADMAN);
	}
}
