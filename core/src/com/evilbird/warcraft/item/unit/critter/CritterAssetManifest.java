/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.critter;

import com.evilbird.engine.common.text.CaseUtils;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Specifies the path to assets required to display a {@link Critter}, as well
 * as to play any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class CritterAssetManifest
{
    private String base;
    private String decompose;
    private String selected;
    private String die;

    public CritterAssetManifest(UnitType type) {
        String name = CaseUtils.toSnakeCase(type.name());
        this.base = "data/textures/neutral/unit/" + name + ".png";
        this.decompose = "data/textures/common/unit/decompose.png";
        this.die = "data/sounds/neutral/unit/" + name + "/annoyed/1.mp3";
        this.selected = "data/sounds/neutral/unit/" + name + "/selected/1.mp3";
    }

    public String getBaseTexturePath() {
        return base;
    }

    public String getDecomposeTexturePath() {
        return decompose;
    }

    public String getSelectedSoundEffectPath() {
        return selected;
    }

    public String getDieSoundEffectPath() {
        return die;
    }
}
