package com.hotkeyablemenuswaps;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.client.config.Keybind;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
enum BookOfTheDeadSwap {
    HOSIDIUS("lunch by the lancalliums", HotkeyableMenuSwapsConfig::botdHosidiusHotKey),
    PISCARILIUS("the fisher's flute", HotkeyableMenuSwapsConfig::botdPiscariliusHotKey),
    SHAYZEIN("history and hearsay", HotkeyableMenuSwapsConfig::botdShayzeinHotKey),
    LOVAKENGJ("jewellery of jubilation", HotkeyableMenuSwapsConfig::botdLovakengjHotKey),
    ARCEUUS("a dark disposition", HotkeyableMenuSwapsConfig::botdArceuusHotKey),
    ;

    private final String menuOptionName;
    private final Function<HotkeyableMenuSwapsConfig, Keybind> keybindFunction;

    public Keybind getKeybind(HotkeyableMenuSwapsConfig config) {
        return keybindFunction.apply(config);
    }
}
