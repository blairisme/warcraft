/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control;

import com.evilbird.engine.common.text.CaseUtils;
import com.evilbird.warcraft.common.WarcraftFaction;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Specifies the path to assets required to display a {@link ControlPane}.
 *
 * @author Blair Butterworth
 */
public class ControlPaneAssetManifest
{
    private String icons;
    private String iconsDisabled;
    private String actionButton;
    private String deselectButton;
    private String actionPanel;
    private String detailsPanel;
    private String menuPanel;
    private String minimapPanel;
    private String selectionPanel;
    private String unitPanel;
    private String buttonEnabled;
    private String buttonSelected;
    private String buttonDisabled;
    private String buildingFill;
    private String buildingBackground;
    private String healthProgressHigh;
    private String healthProgressMedium;
    private String healthProgressLow;
    private Collection<String> textures;
    
    public ControlPaneAssetManifest(WarcraftFaction faction) {
        setCommonTextures();
        setFactionTextures(faction);
        setTexturesList();
    }

    private void setCommonTextures() {
        icons = "data/textures/common/menu/icons.png";
        iconsDisabled = "data/textures/common/menu/icons_disabled.png";
        actionButton = "data/textures/common/menu/action.png";
        deselectButton = "data/textures/common/menu/unselect.png";
        buildingFill = "data/textures/common/menu/building_progress_bar.png";
        buildingBackground="data/textures/common/menu/building_progress_background.png";
        healthProgressHigh = "data/textures/common/menu/health_bar_high.png";
        healthProgressMedium = "data/textures/common/menu/health_bar_medium.png";
        healthProgressLow = "data/textures/common/menu/health_bar_low.png";
        unitPanel = "data/textures/common/menu/selection.png";
    }

    private void setFactionTextures(WarcraftFaction unitFaction) {
        String faction = CaseUtils.toSnakeCase(unitFaction.name());
        actionPanel = "data/textures/" + faction + "/menu/action_panel.png";
        detailsPanel = "data/textures/" + faction + "/menu/details_panel.png";
        menuPanel = "data/textures/" + faction + "/menu/menu_panel.png";
        minimapPanel = "data/textures/" + faction + "/menu/minimap_panel.png";
        selectionPanel = "data/textures/" + faction + "/menu/selection_panel.png";
        buttonEnabled = "data/textures/" + faction + "/menu/button-thin-medium-normal.png";
        buttonSelected = "data/textures/" + faction + "/menu/button-thin-medium-pressed.png";
        buttonDisabled = "data/textures/" + faction + "/menu/button-thin-medium-grayed.png";
    }
    
    private void setTexturesList() {
        textures = new ArrayList<>();
        textures.add(icons);
        textures.add(iconsDisabled);
        textures.add(actionButton);
        textures.add(deselectButton);
        textures.add(buildingFill);
        textures.add(buildingBackground);
        textures.add(healthProgressHigh);
        textures.add(healthProgressMedium);
        textures.add(healthProgressLow);
        textures.add(unitPanel);
        textures.add(actionPanel);
        textures.add(detailsPanel);
        textures.add(menuPanel);
        textures.add(minimapPanel);
        textures.add(selectionPanel);
        textures.add(buttonEnabled);
        textures.add(buttonSelected);
        textures.add(buttonDisabled);
    }

    public String getIcons() {
        return icons;
    }

    public String getDisabledIcons() {
        return iconsDisabled;
    }

    public String getActionButton() {
        return actionButton;
    }

    public String getDeselectButton() {
        return deselectButton;
    }

    public String getActionPanel() {
        return actionPanel;
    }

    public String getDetailsPanel() {
        return detailsPanel;
    }

    public String getMenuPanel() {
        return menuPanel;
    }

    public String getMinimapPanel() {
        return minimapPanel;
    }

    public String getSelectionPanel() {
        return selectionPanel;
    }

    public String getUnitPanel() {
        return unitPanel;
    }

    public String getButtonEnabled() {
        return buttonEnabled;
    }

    public String getButtonSelected() {
        return buttonSelected;
    }

    public String getButtonDisabled() {
        return buttonDisabled;
    }

    public String getBuildingFill() {
        return buildingFill;
    }

    public String getBuildingBackground() {
        return buildingBackground;
    }

    public String getHealthProgressHigh() {
        return healthProgressHigh;
    }

    public String getHealthProgressMedium() {
        return healthProgressMedium;
    }

    public String getHealthProgressLow() {
        return healthProgressLow;
    }

    public Collection<String> getTextures() {
        return textures;
    }
}
