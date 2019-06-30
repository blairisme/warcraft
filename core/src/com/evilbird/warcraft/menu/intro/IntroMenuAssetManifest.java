/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Specifies the paths of assets required to display an {@link IntroMenu}, as
 * well as to play any sound effects used by it, especially narration.
 *
 * @author Blair Butterworth
 */
public class IntroMenuAssetManifest
{
    private String font;
    private String button;
    private String music;
    private String strings;
    private String background;
    private List<String> narration;

    public IntroMenuAssetManifest(IntroMenuType type) {
        String faction = getFaction(type);
        String name = getName(type);
        background = "data/textures/" + faction + "/menu/" + name + ".png";
        strings = "data/strings/" + faction + "/menu/" + name;
        narration = narration(type, faction, name);
        font = "data/fonts/philosopher.ttf";
        button = "data/textures/common/menu/button.png";
        music = "data/music/4.mp3";
    }

    private List<String> narration(IntroMenuType type, String faction, String name) {
        if (type.isHuman()) {
            return humanNarration(type, faction, name);
        }
        return orcNarration(type, faction, name);
    }

    private List<String> humanNarration(IntroMenuType type, String faction, String name) {
        switch (type) {
            case Human6:
            case Human10:
            case Human12:
            case Human14: return getSingleNarration(faction, name);
            default: return getMultipleNarration(faction, name);
        }
    }

    private List<String> orcNarration(IntroMenuType type, String faction, String name) {
        switch (type) {
            case Human3:
            case Human7:
            case Human11:
            case Human12:
            case Human13: return getMultipleNarration(faction, name);
            default: return getSingleNarration(faction, name);
        }
    }

    private List<String> getSingleNarration(String faction, String name) {
        return Collections.singletonList(
            "data/sounds/" + faction + "/menu/" + name + ".mp3");
    }

    private List<String> getMultipleNarration(String faction, String name) {
        return Arrays.asList(
            "data/sounds/" + faction + "/menu/" + name + "a.mp3",
            "data/sounds/" + faction + "/menu/" + name + "b.mp3");
    }

    public String getFont() {
        return font;
    }

    public String getButton() {
        return button;
    }

    public String getMusic() {
        return music;
    }

    public String getStrings() {
        return strings;
    }

    public String getBackground() {
        return background;
    }

    public List<String> getNarration() {
        return narration;
    }

    private String getFaction(IntroMenuType type) {
        return type.isHuman() ? "human" : "orc";
    }

    private String getName(IntroMenuType type) {
        return "intro" + type.getIndex();
    }
}
