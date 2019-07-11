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
import com.evilbird.warcraft.item.unit.UnitFaction;
import com.evilbird.warcraft.item.unit.UnitType;

import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.UnitType.Peon;

/**
 * Specifies the paths of assets required to display a {@link Gatherer}, as
 * well as to play any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class GathererAssetManifest
{
    private String base;
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

    public GathererAssetManifest(UnitType type) {
        String name = getName(type);
        boolean landBased = isLandGatherer(type);

        setTextures(name, type, landBased);
        setSounds(name, type, landBased);
    }

    private void setTextures(String name, UnitType type, boolean landBased) {
        String faction = getFaction(type);
        base = "data/textures/" + faction + "/unit/" + name + ".png";
        decompose = "data/textures/common/unit/decompose.png";

        if (landBased) {
            moveWithGold = "data/textures/" + faction + "/unit/" + name + "_with_gold.png";
            moveWithWood = "data/textures/" + faction + "/unit/" + name + "_with_wood.png";
        }
    }

    private void setSounds(String name, UnitType type, boolean landBased) {
        setFactionSounds(name, type.getFaction());
        if (landBased) {
            setLandGathererSounds();
        } else {
            setSeaGathererSounds();
        }
    }

    private void setLandGathererSounds() {
        chopping = "data/sounds/common/unit/chopping/";
        attack = "data/sounds/common/unit/attack/fist/1.mp3";
        construct = "data/sounds/common/unit/construct/1.mp3";
    }

    private void setSeaGathererSounds() {
        attack = "data/sounds/common/unit/attack/siege/1.mp3";
        construct = "data/sounds/common/unit/construct/1.mp3";
    }

    private void setFactionSounds(String name, UnitFaction faction) {
        dead = "data/sounds/" + faction + "/unit/common/dead/1.mp3";
        acknowledge = "data/sounds/" + faction + "/unit/" + name + "/acknowledge/";
        selected = "data/sounds/" + faction + "/unit/" + name + "/selected/";
        complete = "data/sounds/" + faction + "/unit/" + name + "/complete/1.mp3";
        ready = "data/sounds/" + faction + "/unit/" + name + "/ready/1.mp3";
    }

    public String getBaseTexturePath() {
        return base;
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

    private boolean isLandGatherer(UnitType type) {
        return type == Peasant || type == Peon;
    }
}
