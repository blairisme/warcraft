/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.evilbird.engine.common.text.CaseUtils;
import com.evilbird.warcraft.common.WarcraftFaction;

/**
 * Specifies the paths of assets required to display an {@link OutroMenu}, as
 * well as to play any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class OutroMenuAssetManifest
{
    private String font;
    private String fontLarge;
    private String button;
    private String Strings;
    private String backgroundVictory;
    private String backgroundDefeat;
    private String progressBackground;
    private String progressFill;
    
    public OutroMenuAssetManifest(WarcraftFaction faction) {
        setCommonAssets();
        setFactionAssets(faction);
    }

    private void setCommonAssets() {
        font = "data/fonts/philosopher.ttf";
        fontLarge = "data/fonts/philosopher-large.ttf";
        button = "data/textures/common/menu/button.png";
        Strings = "data/Strings/common/menu/outro";
        progressFill = "data/textures/common/menu/stats_progress_fill.png";
        progressBackground = "data/textures/common/menu/stats_progress_background.png";
    }

    private void setFactionAssets(WarcraftFaction warcraftFaction) {
        String faction = CaseUtils.toSnakeCase(warcraftFaction.name());
        backgroundVictory = "data/textures/" + faction + "/menu/victory.png";
        backgroundDefeat = "data/textures/" + faction + "/menu/defeat.png";
    }

    public String getFont() {
        return font;
    }

    public String getFontLarge() {
        return fontLarge;
    }

    public String getButton() {
        return button;
    }

    public String getStrings() {
        return Strings;
    }

    public String getVictoryBackground() {
        return backgroundVictory;
    }

    public String getDefeatBackground() {
        return backgroundDefeat;
    }

    public String getProgressFill() {
        return progressFill;
    }

    public String getProgressBackground() {
        return progressBackground;
    }
}
