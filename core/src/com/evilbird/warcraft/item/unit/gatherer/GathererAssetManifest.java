/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer;

import com.evilbird.engine.common.text.CaseUtils;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Specifies the paths of assets required to display a {@link Gatherer}, as
 * well as to play any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class GathererAssetManifest
{
    private String base;
    private String icons;
    private String decompose;
    private String moveWithGold;
    private String moveWithWood;
    private String chopping;
    private String selected;
    private String acknowledge;
    private String attack;
    private String complete;
    private String construct;
    private String ready;
    private String dead;

    public GathererAssetManifest(UnitType unitType) {
        String faction = getFaction(unitType);
        String name = getName(unitType);
        base = "data/textures/" + faction + "/perennial/" + name + ".png";
        icons = "data/textures/neutral/perennial/icons.png";
        decompose = "data/textures/neutral/perennial/decompose.png";
        moveWithGold = "data/textures/" + faction + "/perennial/" + name + "_move_gold.png";
        moveWithWood = "data/textures/" + faction + "/perennial/" + name + "_move_wood.png";
        chopping = "data/sounds/common/unit/chopping/";
        selected = "data/sounds/" + faction + "/unit/" + name + "/selected/";
        acknowledge = "data/sounds/" + faction + "/unit/" + name + "/acknowledge/";
        attack = "data/sounds/" + faction + "/unit/" + name + "/attack/1.mp3";
        complete = "data/sounds/" + faction + "/unit/" + name + "/complete/1.mp3";
        construct = "data/sounds/" + faction + "/unit/" + name + "/construct/1.mp3";
        ready = "data/sounds/" + faction + "/unit/" + name + "/ready/1.mp3";
        dead = "data/sounds/" + faction + "/unit/common/dead/1.mp3";
    }

    public String getBaseTexturePath() {
        return base;
    }

    public String getIconTexturePath() {
        return icons;
    }

    public String getDecomposeTexturePath() {
        return decompose;
    }

    public String getMoveWithGoldTexturePath() {
        return moveWithGold;
    }

    public String getMoveWithWoodTexturePath() {
        return moveWithWood;
    }

    public String getChoppingSoundEffectPath() {
        return chopping;
    }

    public String getSelectedSoundEffectPath() {
        return selected;
    }

    public String getAcknowledgeSoundEffectPath() {
        return acknowledge;
    }

    public String getAttackSoundEffectPath() {
        return attack;
    }

    public String getCompleteSoundEffectPath() {
        return complete;
    }

    public String getConstructSoundEffectPath() {
        return construct;
    }

    public String getReadySoundEffectPath() {
        return ready;
    }

    public String getDeadSoundEffectPath() {
        return dead;
    }

    private String getName(UnitType unitType) {
        return CaseUtils.toSnakeCase(unitType.name());
    }

    private String getFaction(UnitType unitType) {
        return CaseUtils.toSnakeCase(unitType.getFaction().name());
    }
}
