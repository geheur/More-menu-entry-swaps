package com.hotkeyablemenuswaps;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.EnumComposition;
import net.runelite.api.EnumID;
import net.runelite.api.GameState;
import net.runelite.api.ItemContainer;
import net.runelite.api.ItemID;
import net.runelite.api.Varbits;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.eventbus.Subscribe;

@Slf4j
public class RunepouchUtils
{
	@Inject private Client client;

	BiMap<Integer, Integer> runeIDItemIDBiMap;

	public void startUp()
	{
		if (client.getGameState().getState() >= GameState.LOGIN_SCREEN.getState()) {
			createRuneIDItemIDBiMap();
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGING_IN || gameStateChanged.getGameState() == GameState.HOPPING)
		{
			createRuneIDItemIDBiMap();
		}
	}

	public boolean inventoryContainsRunePouch(ItemContainer inventory)
	{
		return inventory.contains(ItemID.RUNE_POUCH) ||
			   inventory.contains(ItemID.RUNE_POUCH_L) ||
			   inventory.contains(ItemID.DIVINE_RUNE_POUCH) ||
			   inventory.contains(ItemID.DIVINE_RUNE_POUCH_L);
	}

	public boolean canStoreItemInRunePouch(int itemID, int quantity)
	{
		int[] pouchRunes = {Varbits.RUNE_POUCH_RUNE1, Varbits.RUNE_POUCH_RUNE2, Varbits.RUNE_POUCH_RUNE3, Varbits.RUNE_POUCH_RUNE4, Varbits.RUNE_POUCH_RUNE5, Varbits.RUNE_POUCH_RUNE6};
		int[] pouchAmounts = {Varbits.RUNE_POUCH_AMOUNT1, Varbits.RUNE_POUCH_AMOUNT2, Varbits.RUNE_POUCH_AMOUNT3, Varbits.RUNE_POUCH_AMOUNT4, Varbits.RUNE_POUCH_AMOUNT5, Varbits.RUNE_POUCH_AMOUNT6};
		for (int i = 0; i < 6; i++) {
			if (itemID == getItemIDFromRuneID(client.getVarbitValue(pouchRunes[i]))) {
				return client.getVarbitValue(pouchAmounts[i]) + quantity <= 16000;
			}
		}
		return false;
	}

	public boolean isItemARune(int itemID)
	{
		return runeIDItemIDBiMap.inverse().containsKey(itemID);
	}

	private void createRuneIDItemIDBiMap()
	{
		if (runeIDItemIDBiMap != null) return;
		// getIntVals starts at index=1 -> Rune pouch rune starts from 1 and goes to n (22 runes)
		EnumComposition ec = client.getEnum(EnumID.RUNEPOUCH_RUNE);
		int[] runeIDs = ec.getKeys();
		int[] itemIDs = ec.getIntVals();
		runeIDItemIDBiMap = HashBiMap.create(runeIDs.length);
		for (int i = 0; i < runeIDs.length; i++)
		{
			runeIDItemIDBiMap.put(runeIDs[i], itemIDs[i]);
		}
	}

	public int getItemIDFromRuneID(int runeID)
	{
		return runeIDItemIDBiMap.getOrDefault(runeID, -1);
	}
}
