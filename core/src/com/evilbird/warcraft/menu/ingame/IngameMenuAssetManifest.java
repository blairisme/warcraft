/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.evilbird.engine.common.text.CaseUtils;
import com.evilbird.warcraft.common.WarcraftFaction;

/**
 * Specifies the paths of assets required to display a {@link IngameMenu}.
 *
 * @author Blair Butterworth
 */
public class IngameMenuAssetManifest
{
    private String buttonClick;
    private String buttonEnabled;
    private String buttonDisabled;
    private String buttonSelected;
    private String textPanelNormal;
    private String backgroundNormal;
    private String backgroundWide;
    private String backgroundSmall;
   
    public IngameMenuAssetManifest(WarcraftFaction unitFaction) {
        String faction = CaseUtils.toSnakeCase(unitFaction.name());
        buttonEnabled = "data/textures/" + faction + "/menu/button-large-normal.png";
        buttonDisabled = "data/textures/" + faction + "/menu/button-large-grayed.png";
        buttonSelected = "data/textures/" + faction + "/menu/button-large-pressed.png";
        textPanelNormal = "data/textures/" + faction + "/menu/text_panel_normal.png";
        backgroundNormal = "data/textures/" + faction + "/menu/panel_normal.png";
        backgroundWide = "data/textures/" + faction + "/menu/panel_wide.png";
        backgroundSmall = "data/textures/" + faction + "/menu/panel_small.png";
        buttonClick = "data/sounds/common/menu/click.mp3";
    }

    public String getButtonClick() {
        return buttonClick;
    }

    public String getButtonEnabled() {
        return buttonEnabled;
    }

    public String getButtonDisabled() {
        return buttonDisabled;
    }

    public String getButtonSelected() {
        return buttonSelected;
    }

    public String getTextPanelNormal() {
        return textPanelNormal;
    }

    public String getBackgroundNormal() {
        return backgroundNormal;
    }

    public String getBackgroundWide() {
        return backgroundWide;
    }

    public String getBackgroundSmall() {
        return backgroundSmall;
    }
}
