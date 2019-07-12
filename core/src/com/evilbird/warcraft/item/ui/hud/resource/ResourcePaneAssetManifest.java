/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.resource;

import com.evilbird.engine.common.text.CaseUtils;
import com.evilbird.warcraft.item.unit.UnitFaction;

/**
 * Specifies the path to assets required to display a {@link ResourcePane}.
 *
 * @author Blair Butterworth
 */
public class ResourcePaneAssetManifest
{
    private String icons;
    private String background;

    public ResourcePaneAssetManifest(UnitFaction unitFaction) {
        String faction = CaseUtils.toSnakeCase(unitFaction.name());
        icons = "data/textures/common/menu/resource_icon.png";
        background = "data/textures/" + faction + "/menu/resource_panel.png";
    }

    public String getIcons() {
        return icons;
    }

    public String getBackground() {
        return background;
    }
}
