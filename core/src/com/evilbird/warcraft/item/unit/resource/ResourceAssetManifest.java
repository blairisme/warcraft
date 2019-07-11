/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.evilbird.engine.common.text.CaseUtils;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Specifies the path to assets required to display a {@link Resource}, as well
 * as to play any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class ResourceAssetManifest
{
    private String base;
    private String destruction;
    private String destroyed;
    private String selected;

    public ResourceAssetManifest(UnitType type) {
        String name = getName(type);
        base = "data/textures/neutral/resource/winter/" + name + ".png";
        destruction = "data/textures/common/building/winter/destroyed_site.png";
        destroyed = "data/sounds/common/building/destroyed/";
        selected = "data/sounds/neutral/resource/" + name + "/selected/1.mp3";
    }

    public String getGeneralTexturePath() {
        return base;
    }

    public String getDestructionTexturePath() {
        return destruction;
    }

    public String getDestroyedSoundEffectPath() {
        return destroyed;
    }

    public String getSelectedSoundEffectPath() {
        return selected;
    }

    private String getName(UnitType unitType) {
        return CaseUtils.toSnakeCase(unitType.name());
    }
}
