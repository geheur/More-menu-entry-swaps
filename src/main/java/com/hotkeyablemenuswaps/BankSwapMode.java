package com.hotkeyablemenuswaps;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.MenuAction;
import net.runelite.client.config.Keybind;
import net.runelite.client.plugins.menuentryswapper.ShiftDepositMode;
import net.runelite.client.plugins.menuentryswapper.ShiftWithdrawMode;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
enum BankSwapMode implements HotkeyableMenuSwapsPlugin.hasKeybind {
    OFF(ShiftDepositMode.OFF, ShiftWithdrawMode.OFF, config -> Keybind.NOT_SET),
    SWAP_1(ShiftDepositMode.DEPOSIT_1, ShiftWithdrawMode.WITHDRAW_1, HotkeyableMenuSwapsConfig::getBankSwap1Hotkey, 2, 1),
    SWAP_5(ShiftDepositMode.DEPOSIT_5, ShiftWithdrawMode.WITHDRAW_5, HotkeyableMenuSwapsConfig::getBankSwap5Hotkey, 3, 2),
    SWAP_10(ShiftDepositMode.DEPOSIT_10, ShiftWithdrawMode.WITHDRAW_10, HotkeyableMenuSwapsConfig::getBankSwap10Hotkey, 4, 3),
    SWAP_X(ShiftDepositMode.DEPOSIT_X, ShiftWithdrawMode.WITHDRAW_X, HotkeyableMenuSwapsConfig::getBankSwapXHotkey, 5, 5),
    SWAP_SET_X(7, 6, 5, 6 /* TODO is this correct? */, MenuAction.CC_OP_LOW_PRIORITY, 6, 5, 6, -1, HotkeyableMenuSwapsConfig::getBankSwapSetXHotkey),
    SWAP_ALL(ShiftDepositMode.DEPOSIT_ALL, ShiftWithdrawMode.WITHDRAW_ALL, HotkeyableMenuSwapsConfig::getBankSwapAllHotkey, 7, 4),
    SWAP_ALL_BUT_1(-1, -1, -1, -1, ShiftWithdrawMode.WITHDRAW_ALL_BUT_1.getMenuAction(), ShiftWithdrawMode.WITHDRAW_ALL_BUT_1.getIdentifier(), ShiftWithdrawMode.WITHDRAW_ALL_BUT_1.getIdentifierChambersStorageUnit(), 7, 4, HotkeyableMenuSwapsConfig::getBankSwapAllBut1Hotkey),
    SWAP_EXTRA_OP(ShiftDepositMode.EXTRA_OP, ShiftWithdrawMode.OFF, HotkeyableMenuSwapsConfig::getBankSwapExtraOpHotkey),
    LOCK_UNLOCK_SLOT(10, 10, -1, -1, ShiftWithdrawMode.OFF.getMenuAction(), -1, -1, -1, -1, HotkeyableMenuSwapsConfig::lockUnlockSlotHotkey)
    ;

    private final int depositIdentifier;
    private final int depositIdentifierDepositBox;
    private final int depositIdentifierChambersStorageUnit;
	private final int depositIdentifierGroupStorage;

	private final MenuAction withdrawMenuAction;
    private final int withdrawIdentifier;
    private final int withdrawIdentifierChambersStorageUnit;

	private final int identifierSeedVault;
	private final int priceCheckerIdentifier;

	private final Function<HotkeyableMenuSwapsConfig, Keybind> keybindFunction;

	BankSwapMode(ShiftDepositMode depositMode, ShiftWithdrawMode withdrawMode, Function<HotkeyableMenuSwapsConfig, Keybind> keybindFunction) {
		this(depositMode, withdrawMode, keybindFunction, -1, -1);
	}

	BankSwapMode(ShiftDepositMode depositMode, ShiftWithdrawMode withdrawMode, Function<HotkeyableMenuSwapsConfig, Keybind> keybindFunction, int identifierSeedVault, int priceCheckerIdentifier) {
        depositIdentifier = depositMode.getIdentifier();
        depositIdentifierDepositBox = depositMode.getIdentifierDepositBox();
        depositIdentifierChambersStorageUnit = depositMode.getIdentifierChambersStorageUnit();
        depositIdentifierGroupStorage = depositMode.getIdentifierGroupStorage();
        withdrawMenuAction = withdrawMode.getMenuAction();
        withdrawIdentifier = withdrawMode.getIdentifier();
        withdrawIdentifierChambersStorageUnit = withdrawMode.getIdentifierChambersStorageUnit();
		this.keybindFunction = keybindFunction;
		this.identifierSeedVault = identifierSeedVault;
		this.priceCheckerIdentifier = priceCheckerIdentifier;
	}

    public Keybind getKeybind(HotkeyableMenuSwapsConfig config) {
        return keybindFunction.apply(config);
    }
}
