package com.hotkeyablemenuswaps;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Keybind;

import static com.hotkeyablemenuswaps.OccultAltarSwap.*;

@ConfigGroup("hotkeyablemenuswaps")
public interface HotkeyableMenuSwapsConfig extends Config
{
	@ConfigSection(name = "Custom Swaps", description = "Text-based custom swaps", position = -110, closedByDefault = true)
	String customSwapsSection = "Custom Swaps";

	@ConfigItem(
		keyName = "customSwaps",
		name = "Custom swaps",
		description = "Options to swap to the top.",
		section = customSwapsSection,
		position = 0
	)
	default String customSwaps() {
		return "";
	}

	@ConfigSection(name = "Custom Swaps (shift)", description = "Text-based custom swaps", position = -100, closedByDefault = true)
	String customShiftSwapsSection = "Custom Swaps (shift)";

	@ConfigItem(
		keyName = "customShiftSwaps",
		name = "Custom swaps (shift)",
		description = "Options to swap to the top when shift is held.",
		section = customShiftSwapsSection,
		position = 0
	)
	default String customShiftSwaps() {
		return "";
	}

	@ConfigSection(name = "Custom Swaps (hotkeys)", description = "Hotkey-based swaps.", position = -90, closedByDefault = true)
	String hotkeys = "hotkeys";

	@ConfigItem(keyName = "hotkey1", name = "hotkey 1", description = "", section = hotkeys, position = 0) default Keybind hotkey1() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey1Swaps", name = "", description = "", section = hotkeys, position = 1) default String hotkey1Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey2", name = "hotkey 2", description = "", section = hotkeys, position = 2) default Keybind hotkey2() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey2Swaps", name = "", description = "", section = hotkeys, position = 3) default String hotkey2Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey3", name = "hotkey 3", description = "", section = hotkeys, position = 4) default Keybind hotkey3() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey3Swaps", name = "", description = "", section = hotkeys, position = 5) default String hotkey3Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey4", name = "hotkey 4", description = "", section = hotkeys, position = 6) default Keybind hotkey4() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey4Swaps", name = "", description = "", section = hotkeys, position = 7) default String hotkey4Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey5", name = "hotkey 5", description = "", section = hotkeys, position = 8) default Keybind hotkey5() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey5Swaps", name = "", description = "", section = hotkeys, position = 9) default String hotkey5Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey6", name = "hotkey 6", description = "", section = hotkeys, position = 10) default Keybind hotkey6() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey6Swaps", name = "", description = "", section = hotkeys, position = 11) default String hotkey6Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey7", name = "hotkey 7", description = "", section = hotkeys, position = 12) default Keybind hotkey7() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey7Swaps", name = "", description = "", section = hotkeys, position = 13) default String hotkey7Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey8", name = "hotkey 8", description = "", section = hotkeys, position = 14) default Keybind hotkey8() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey8Swaps", name = "", description = "", section = hotkeys, position = 15) default String hotkey8Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey9", name = "hotkey 9", description = "", section = hotkeys, position = 16) default Keybind hotkey9() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey9Swaps", name = "", description = "", section = hotkeys, position = 17) default String hotkey9Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey10", name = "hotkey 10", description = "", section = hotkeys, position = 18) default Keybind hotkey10() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey10Swaps", name = "", description = "", section = hotkeys, position = 19) default String hotkey10Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey11", name = "hotkey 11", description = "", section = hotkeys, position = 20) default Keybind hotkey11() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey11Swaps", name = "", description = "", section = hotkeys, position = 21) default String hotkey11Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey12", name = "hotkey 12", description = "", section = hotkeys, position = 22) default Keybind hotkey12() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey12Swaps", name = "", description = "", section = hotkeys, position = 23) default String hotkey12Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey13", name = "hotkey 13", description = "", section = hotkeys, position = 24) default Keybind hotkey13() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey13Swaps", name = "", description = "", section = hotkeys, position = 25) default String hotkey13Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey14", name = "hotkey 14", description = "", section = hotkeys, position = 26) default Keybind hotkey14() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey14Swaps", name = "", description = "", section = hotkeys, position = 27) default String hotkey14Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey15", name = "hotkey 15", description = "", section = hotkeys, position = 28) default Keybind hotkey15() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey15Swaps", name = "", description = "", section = hotkeys, position = 29) default String hotkey15Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey16", name = "hotkey 16", description = "", section = hotkeys, position = 30) default Keybind hotkey16() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey16Swaps", name = "", description = "", section = hotkeys, position = 31) default String hotkey16Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey17", name = "hotkey 17", description = "", section = hotkeys, position = 32) default Keybind hotkey17() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey17Swaps", name = "", description = "", section = hotkeys, position = 33) default String hotkey17Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey18", name = "hotkey 18", description = "", section = hotkeys, position = 34) default Keybind hotkey18() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey18Swaps", name = "", description = "", section = hotkeys, position = 35) default String hotkey18Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey19", name = "hotkey 19", description = "", section = hotkeys, position = 36) default Keybind hotkey19() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey19Swaps", name = "", description = "", section = hotkeys, position = 37) default String hotkey19Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey20", name = "hotkey 20", description = "", section = hotkeys, position = 38) default Keybind hotkey20() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey20Swaps", name = "", description = "", section = hotkeys, position = 39) default String hotkey20Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey21", name = "hotkey 21", description = "", section = hotkeys, position = 40) default Keybind hotkey21() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey21Swaps", name = "", description = "", section = hotkeys, position = 41) default String hotkey21Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey22", name = "hotkey 22", description = "", section = hotkeys, position = 42) default Keybind hotkey22() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey22Swaps", name = "", description = "", section = hotkeys, position = 43) default String hotkey22Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey23", name = "hotkey 23", description = "", section = hotkeys, position = 44) default Keybind hotkey23() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey23Swaps", name = "", description = "", section = hotkeys, position = 45) default String hotkey23Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey24", name = "hotkey 24", description = "", section = hotkeys, position = 46) default Keybind hotkey24() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey24Swaps", name = "", description = "", section = hotkeys, position = 47) default String hotkey24Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey25", name = "hotkey 25", description = "", section = hotkeys, position = 48) default Keybind hotkey25() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey25Swaps", name = "", description = "", section = hotkeys, position = 49) default String hotkey25Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey26", name = "hotkey 26", description = "", section = hotkeys, position = 50) default Keybind hotkey26() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey26Swaps", name = "", description = "", section = hotkeys, position = 51) default String hotkey26Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey27", name = "hotkey 27", description = "", section = hotkeys, position = 52) default Keybind hotkey27() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey27Swaps", name = "", description = "", section = hotkeys, position = 53) default String hotkey27Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey28", name = "hotkey 28", description = "", section = hotkeys, position = 54) default Keybind hotkey28() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey28Swaps", name = "", description = "", section = hotkeys, position = 55) default String hotkey28Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey29", name = "hotkey 29", description = "", section = hotkeys, position = 56) default Keybind hotkey29() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey29Swaps", name = "", description = "", section = hotkeys, position = 57) default String hotkey29Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey30", name = "hotkey 30", description = "", section = hotkeys, position = 58) default Keybind hotkey30() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey30Swaps", name = "", description = "", section = hotkeys, position = 59) default String hotkey30Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey31", name = "hotkey 31", description = "", section = hotkeys, position = 60) default Keybind hotkey31() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey31Swaps", name = "", description = "", section = hotkeys, position = 61) default String hotkey31Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey32", name = "hotkey 32", description = "", section = hotkeys, position = 62) default Keybind hotkey32() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey32Swaps", name = "", description = "", section = hotkeys, position = 63) default String hotkey32Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey33", name = "hotkey 33", description = "", section = hotkeys, position = 64) default Keybind hotkey33() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey33Swaps", name = "", description = "", section = hotkeys, position = 65) default String hotkey33Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey34", name = "hotkey 34", description = "", section = hotkeys, position = 66) default Keybind hotkey34() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey34Swaps", name = "", description = "", section = hotkeys, position = 67) default String hotkey34Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey35", name = "hotkey 35", description = "", section = hotkeys, position = 68) default Keybind hotkey35() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey35Swaps", name = "", description = "", section = hotkeys, position = 69) default String hotkey35Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey36", name = "hotkey 36", description = "", section = hotkeys, position = 70) default Keybind hotkey36() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey36Swaps", name = "", description = "", section = hotkeys, position = 71) default String hotkey36Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey37", name = "hotkey 37", description = "", section = hotkeys, position = 72) default Keybind hotkey37() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey37Swaps", name = "", description = "", section = hotkeys, position = 73) default String hotkey37Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey38", name = "hotkey 38", description = "", section = hotkeys, position = 74) default Keybind hotkey38() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey38Swaps", name = "", description = "", section = hotkeys, position = 75) default String hotkey38Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey39", name = "hotkey 39", description = "", section = hotkeys, position = 76) default Keybind hotkey39() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey39Swaps", name = "", description = "", section = hotkeys, position = 77) default String hotkey39Swaps() { return ""; }
	@ConfigItem(keyName = "hotkey40", name = "hotkey 40", description = "", section = hotkeys, position = 78) default Keybind hotkey40() { return Keybind.NOT_SET; }
	@ConfigItem(keyName = "hotkey40Swaps", name = "", description = "", section = hotkeys, position = 79) default String hotkey40Swaps() { return ""; }

	@ConfigSection(name = "Custom Hides", description = "Text-based custom menu entry hides", position = -9, closedByDefault = true)
	String customHidesSection = "Custom Hides";

	@ConfigItem(
		keyName = "customHides",
		name = "Custom hides",
		description = "Options to remove from the menu.",
		section = customHidesSection,
		position = 1
	)
	default String customHides() {
		return "";
	}

	@ConfigSection(name = "Custom swap instructions", description = "instructions", position = -7, closedByDefault = true)
	String customSwapInstructions = "Custom swap instructions";

	@ConfigItem(
		keyName = "customSwapperInstructions",
		name = "Instructions",
		description = "Options to swap to the top.",
		section = customSwapInstructions,
		position = 2
	)
	default String customSwapperInstructions() {
		return "https://github.com/geheur/More-menu-entry-swaps/wiki/Custom-swaps";
	}

	@ConfigSection(name = "Advanced", description = "Advanced options", position = -6, closedByDefault = true)
	String advancedSection = "Advanced";

	@ConfigItem(
		keyName = "examineCancelLateRemoval",
		name = "Examine/Cancel late removal",
		description = "Keep enabled if you don't understand what this option does.<br>Disabling it can prevent runelite's regular menu swapper from adding its shift-click config options, as well as interfere with cancelling a targeted spell.<br>Specifically, this option prevents the \"Examine\" and \"Cancel\" options from being removed until you open the right-click menu.",
		section = advancedSection,
		position = 0
	)
	default boolean examineCancelLateRemoval() {
		return true;
	}

	@ConfigItem(
		keyName = "doNotSwapOverMinimapOrbs",
		name = "Don't swap minimap orbs",
		description = "Do not swap entries over the minimap orbs such as quick-prayer and special attack.",
		section = advancedSection,
		position = 1
	)
	default boolean doNotSwapOverMinimapOrbs()
	{
		return true;
	}

	@ConfigSection(name = "Ground Item Sort", description = "", position = -8, closedByDefault = true)
	String groundItemSortSection = "Ground Item Sort";

	@ConfigItem(
		keyName = "groundItemSortCustomValues",
		name = "Ground item order",
		description = "Put item names on separate lines in the order you want them to appear in the top of the menu.<br>" +
			"Wildcards (*) are supported.<br>" +
			"You can assign values e.g. \"*defender,100000\" instead of changing the order, but note that lines with no value have a very high value (approximately 2.147b), and items not in the list have value 0.<br>" +
			"Negative values are supported \"bones,-1\".<br>" +
			"Noted items supported via \"noted:iron ore\" \"unnoted:iron ore\".<br>" +
			"\"###highlighted###\" and \"###hidden###\" represent any item that is in those lists in the ground items plugin.<br>" +
			"No support for item quantities.",
		section = groundItemSortSection,
		position = 1
	)
	default String groundItemSortCustomValues() {
		return "";
	}

	@ConfigItem(
		keyName = "doNotSwapDeprioritizedGroundItems",
		name = "Don't swap deprioritized \"Take\"",
		description = "Do not allow custom swaps like \"take,*\" to swap \"Take\" options that are deprioritized (below the \"Walk here\" option), such as those deprioritized via the Ground Items plugin.",
		section = groundItemSortSection,
		position = 2
	)
	default boolean doNotSwapDeprioritizedGroundItems() {
		return false;
	}

	@ConfigItem(
		keyName = "groundItemsPriceSortMode",
		name = "Price Sort",
		description =
			"Grand Exchange: Use Grand Exchange price<br>" +
			"max(GE, High Alch): Use highest of Grand Exchange price and High Alchemy price<br>" +
			"Items you've entered into the ground item sort list will use that list's value instead.",
		section = groundItemSortSection,
		position = 3
	)
	default GroundItemPriceSortMode groundItemsPriceSortMode() {
		return GroundItemPriceSortMode.DISABLED;
	}

	@ConfigItem(
		keyName = "groundItemsInventoryPriority",
		name = "Prioritize Inventory Items",
		description =
			"When the inventory is full, the items will be prioritized by the following:<br>" +
			"1. Items that exist in your inventory (sorted by your price sort mode)" +
			"2. Default sort (sorted by your price sort mode)",
		section = groundItemSortSection,
		position = 4
	)
	default boolean groundItemsInventoryPriority() {
		return false;
	}

	@ConfigSection(name = "Bank", description = "Swap menu entries in the bank, deposit box, seed vault, price checker interface, and chambers storage unit.", position = 0, closedByDefault = true)
	String bankSection = "bank";

	@ConfigItem(
			keyName = "bankSwap1Hotkey",
			name = "1",
			description = "The hotkey which, when held, swaps the withdraw/deposit 1 option",
			section = bankSection,
			position = 0
	)
	default Keybind getBankSwap1Hotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "bankSwap5Hotkey",
			name = "5",
			description = "The hotkey which, when held, swaps the withdraw/deposit 5 option",
			section = bankSection,
			position = 1
	)
	default Keybind getBankSwap5Hotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "bankSwap10Hotkey",
			name = "10",
			description = "The hotkey which, when held, swaps the withdraw/deposit 10 option",
			section = bankSection,
			position = 2
	)
	default Keybind getBankSwap10Hotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "bankSwapXHotkey",
			name = "X",
			description = "The hotkey which, when held, swaps the withdraw/deposit X option",
			section = bankSection,
			position = 3
	)
	default Keybind getBankSwapXHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "bankSwapSetXHotkey",
			name = "Set-X",
			description = "The hotkey which, when held, swaps the withdraw/deposit SetX option",
			section = bankSection,
			position = 4
	)
	default Keybind getBankSwapSetXHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "bankSwapAllHotkey",
			name = "All",
			description = "The hotkey which, when held, swaps the withdraw/deposit All option",
			section = bankSection,
			position = 5
	)
	default Keybind getBankSwapAllHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "bankSwapAllBut1Hotkey",
			name = "All-But-1",
			description = "The hotkey which, when held, swaps the withdraw AllBut1 option",
			section = bankSection,
			position = 6
	)
	default Keybind getBankSwapAllBut1Hotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "bankSwapExtraOpHotkey",
			name = "Eat/Wield/Etc.",
			description = "The hotkey which, when held, swaps the bank's Eat/Wield/etc. option",
			section = bankSection,
			position = 7
	)
	default Keybind getBankSwapExtraOpHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigSection(
			name = "Spirit tree/Fairy ring",
			description = "All options that swap entries on a spirit tree or fairy ring",
			position = 1,
			closedByDefault = true
	)
	String treeRingSection = "treeRing";

	@ConfigItem(
			keyName = "swapZanaris",
			name = "Zanaris",
			description = "The hotkey which swaps \"Zanaris\"",
			section = treeRingSection,
			position = 0
	)
	default Keybind getSwapZanarisHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "swapTree",
			name = "Tree",
			description = "The hotkey which swaps \"Tree\"",
			section = treeRingSection,
			position = 1
	)
	default Keybind getSwapTreeHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "swapConfigure",
			name = "Configure",
			description = "The hotkey which swaps \"Configure\"",
			section = treeRingSection,
			position = 2
	)
	default Keybind getSwapConfigureHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "swapLastDestination",
			name = "Last-destination",
			description = "The hotkey which swaps \"Last-destination\"",
			section = treeRingSection,
			position = 3
	)
	default Keybind getSwapLastDestinationHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigSection(
			name = "Occult Altar/Spellbook swap",
			description = "Occult altar menu entry swaps",
			position = 2,
			closedByDefault = true
	)
	String occultAltarSection = "occultAltar";

	@Getter
	@RequiredArgsConstructor
	enum OccultAltarLeftClick {
		OFF(VENERATE, VENERATE),

		STANDARD_VENERATE(STANDARD, VENERATE),
		STANDARD_ANCIENT(STANDARD, ANCIENT),
		STANDARD_LUNAR(STANDARD, LUNAR),
		STANDARD_ARCEUUS(STANDARD, ARCEUUS),

		ANCIENT_VENERATE(ANCIENT, VENERATE),
		ANCIENT_STANDARD(ANCIENT, STANDARD),
		ANCIENT_LUNAR(ANCIENT, LUNAR),
		ANCIENT_ARCEUUS(ANCIENT, ARCEUUS),

		LUNAR_VENERATE(LUNAR, VENERATE),
		LUNAR_STANDARD(LUNAR, STANDARD),
		LUNAR_ANCIENT(LUNAR, ANCIENT),
		LUNAR_ARCEUUS(LUNAR, ARCEUUS),

		ARCEUUS_VENERATE(ARCEUUS, VENERATE),
		ARCEUUS_STANDARD(ARCEUUS, STANDARD),
		ARCEUUS_ANCIENT(ARCEUUS, ANCIENT),
		ARCEUUS_LUNAR(ARCEUUS, LUNAR),
		;

		private final OccultAltarSwap firstOption;
		private final OccultAltarSwap secondOption;
	}

	@ConfigItem(
			keyName = "occultAltarLeftClick",
			name = "Left click",
			description = "The occult altar's left-click option. The second option is used if you are already on the spellbook of the first option. If you want to swap the spellbook swap spell's left-click option, use the runelite menu entry swapper plugin instead.",
			section = occultAltarSection,
			position = 0
	)
	default OccultAltarLeftClick getOccultAltarLeftClickSwap()
	{
		return OccultAltarLeftClick.OFF;
	}

	@ConfigItem(
			keyName = "swapVenerateHotkey",
			name = "Venerate/Cast",
			description = "The hotkey which swaps \"Venerate\" on the Occult Altar and \"Cast\" on Spellbook swap",
			section = occultAltarSection,
			position = 1
	)
	default Keybind getSwapVenerateHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "swapStandardHotkey",
			name = "Standard",
			description = "The hotkey which swaps \"Standard\"",
			section = occultAltarSection,
			position = 2
	)
	default Keybind getSwapStandardHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "swapAncientHotkey",
			name = "Ancient",
			description = "The hotkey which swaps \"Ancient\"",
			section = occultAltarSection,
			position = 3
	)
	default Keybind getSwapAncientHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "swapLunarHotkey",
			name = "Lunar",
			description = "The hotkey which swaps \"Lunar\"",
			section = occultAltarSection,
			position = 4
	)
	default Keybind getSwapLunarHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "swapArceuusHotkey",
			name = "Arceuus",
			description = "The hotkey which swaps \"Arceuus\"",
			section = occultAltarSection,
			position = 5
	)
	default Keybind getSwapArceuusHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(name=
		"Spellbook swap",
		description = "Make the hotkeys work on spellbook swap.", keyName = "swapSpellbookSwap", section = occultAltarSection, position = 6
	) default boolean swapSpellbookSwap() { return true; }

	@ConfigSection(
		name = "Jewellery box",
		description = "The jewellery box in a player owned house.",
		position = 3,
		closedByDefault = true
	)
	String jewelleryBoxSection = "jewelleryBox";

	@ConfigItem(
		keyName = "swapJewelleryBoxHotkey",
		name = "Swap Jewellery Box",
		description = "swap the second with the first menu option on the Jewellery Box. Left-click swaps can be changed in the default Menu Entry Swapper.",
		section = jewelleryBoxSection,
		position = 0
	)
	default Keybind getSwapJewelleryBoxHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigSection(
		name = "Portal Nexus",
		description = "Things in the player owned house portal nexus.",
		position = 4,
		closedByDefault = true
	)
	String portalNexusSection = "portalNexus";

	@ConfigItem(
		keyName = "portalNexusDestinationSwapHotKey",
		name = "Nexus Destination",
		description = "The configured default destination",
		section = portalNexusSection,
		position = 0
	)
	default Keybind portalNexusDestinationSwapHotKey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "portalNexusTeleportMenuSwapHotKey",
		name = "Nexus Teleport Menu",
		description = "",
		section = portalNexusSection,
		position = 1
	)
	default Keybind portalNexusTeleportMenuSwapHotKey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "portalNexusConfigurationSwapHotKey",
		name = "Nexus Configuration",
		description = "The option named \"Configuration\"",
		section = portalNexusSection,
		position = 2
	)
	default Keybind portalNexusConfigurationSwapHotKey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "pohXericsTalismanLeftClick",
		name = "Xeric's Left Click",
		description = "",
		section = portalNexusSection,
		position = 3
	)
	default HotkeyableMenuSwapsPlugin.PortalNexusXericsTalismanSwap pohXericsTalismanLeftClick()
	{
		return HotkeyableMenuSwapsPlugin.PortalNexusXericsTalismanSwap.DESTINATION;
	}

	@ConfigItem(
		keyName = "pohXericsTalismanDestination",
		name = "Xeric's Destination",
		description = "The configured default destination",
		section = portalNexusSection,
		position = 4
	)
	default Keybind pohXericsTalismanDestination()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "pohXericsTalismanTeleportMenu",
		name = "Xeric's Teleport Menu",
		description = "",
		section = portalNexusSection,
		position = 5
	)
	default Keybind pohXericsTalismanTeleportMenu()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "pohXericsTalismanConfiguration",
		name = "Xeric's Configuration",
		description = "The option named \"Configuration\"",
		section = portalNexusSection,
		position = 6
	)
	default Keybind pohXericsTalismanConfiguration()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "pohDigsitePendantLeftClick",
		name = "Digsite Left Click",
		description = "",
		section = portalNexusSection,
		position = 7
	)
	default HotkeyableMenuSwapsPlugin.PortalNexusDigsitePendantSwap pohDigsitePendantLeftClick()
	{
		return HotkeyableMenuSwapsPlugin.PortalNexusDigsitePendantSwap.DESTINATION;
	}

	@ConfigItem(
		keyName = "pohDigsitePendantDestination",
		name = "Digsite Destination",
		description = "The configured default destination",
		section = portalNexusSection,
		position = 8
	)
	default Keybind pohDigsitePendantDestination()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "pohDigsitePendantTeleportMenu",
		name = "Digsite Teleport Menu",
		description = "",
		section = portalNexusSection,
		position = 9
	)
	default Keybind pohDigsitePendantTeleportMenu()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "pohDigsitePendantConfiguration",
		name = "Digsite Configuration",
		description = "The option named \"Configuration\"",
		section = portalNexusSection,
		position = 10
	)
	default Keybind pohDigsitePendantConfiguration()
	{
		return Keybind.NOT_SET;
	}

	@ConfigSection(
			name = "Inventory",
			description = "Inventory menu entry swaps",
			position = 5,
			closedByDefault = true
	)
	String inventorySection = "inventory";

	@ConfigItem(
			keyName = "swapUseHotkey",
			name = "Swap Use",
			description = "swap \"Use\" on items in the inventory",
			section = inventorySection,
			position = 0
	)
	default Keybind getSwapUseHotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigSection(
		name = "Old options",
		description = "Old options that aren't very useful anymore.",
		position = 6,
		closedByDefault = true
	)
	String oldOptionsSection = "oldOptions";

	@ConfigItem(name = "Max Cape (equipped)",
		keyName = "oldHeading1", description = "", section = oldOptionsSection, position = 0)
	default void oldHeading1() {}

	@ConfigItem(
		keyName = "maxCapeCraftingGuildSwapHotKey",
		name = "Crafting Guild",
		description = "",
		section = oldOptionsSection, position = 1)
	default Keybind maxCapeCraftingGuildSwapHotKey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "maxCapeWarriorsGuildSwapHotKey", 
		name = "Warriors Guild",
		description = "",
		section = oldOptionsSection, position = 2)
	default Keybind maxCapeWarriorsGuildSwapHotKey() {
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "maxCapeTelePOHSwapHotKey", 
		name = "Tele to POH",
		description = "",
		section = oldOptionsSection, position = 3)
	default Keybind maxCapeTelePOHSwapHotKey() {
		return Keybind.NOT_SET;
	}

	@ConfigItem(name = "Book of the dead (equipped)",
		keyName = "oldHeading2", description = "", section = oldOptionsSection, position = 4)
	default void oldHeading2() {}

	@ConfigItem(
		keyName = "botdHosidiusHotKey",
		name = "Hosidius",
		description = "",
		section = oldOptionsSection, position = 5)
	default Keybind botdHosidiusHotKey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "botdPiscariliusHotKey",
		name = "Piscarilius",
		description = "",
		section = oldOptionsSection, position = 6)
	default Keybind botdPiscariliusHotKey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "botdShayzeinHotKey",
		name = "Shayzein",
		description = "",
		section = oldOptionsSection, position = 7)
	default Keybind botdShayzeinHotKey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "botdLovakengjHotKey",
		name = "Lovakengj",
		description = "",
		section = oldOptionsSection, position = 8)
	default Keybind botdLovakengjHotKey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "botdArceuusHotKey",
		name = "Arceuus",
		description = "",
		section = oldOptionsSection, position = 9)
	default Keybind botdArceuusHotKey()
	{
		return Keybind.NOT_SET;
	}
}
