/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.engine.common.text.CaseUtils;
import com.evilbird.warcraft.item.unit.UnitType;

import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;

/**
 * Specifies the path to assets required to display a {@link Combatant}, as well
 * as to play any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class CombatantAssetManifest
{
    private String base;
    private String icons;
    private String decompose;
    private String acknowledge;
    private String selected;
    private String ready;
    private String attack;
    private String hit;
    private String die;
    private String capture;
    private String rescue;

    public CombatantAssetManifest(UnitType unitType) {
        String faction = getFaction(unitType);
        String name = getName(unitType);
        String type = getAttackType(unitType);
        setSounds(faction, name, type);
        setTextures(faction, name);
    }

    private void setSounds(String faction, String name, String type) {
        this.attack = "data/sounds/common/unit/attack/" + type + "/";
        this.hit = "data/sounds/common/unit/hit/" + type + "/";
        this.acknowledge = "data/sounds/" + faction + "/unit/" + name + "/acknowledge/";
        this.selected = "data/sounds/" + faction + "/unit/" + name + "/selected/";
        this.ready = "data/sounds/" + faction + "/unit/" + name + "/ready/";
        this.die = "data/sounds/" + faction + "/unit/common/dead/";
        this.capture = "data/sounds/" + faction + "/unit/common/capture/";
        this.rescue = "data/sounds/" + faction + "/unit/common/rescue/";
    }

    private void setTextures(String faction, String name) {
        this.base = "data/textures/" + faction + "/perennial/" + name + ".png";
        this.icons = "data/textures/neutral/perennial/icons.png";
        this.decompose = "data/textures/neutral/perennial/decompose.png";
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

    public String getAcknowledgeSoundEffectPath() {
        return acknowledge;
    }

    public String getSelectedSoundEffectPath() {
        return selected;
    }

    public String getAttackSoundEffectPath() {
        return attack;
    }

    public String getHitSoundEffectPath() {
        return hit;
    }

    public String getReadySoundEffectPath() {
        return ready;
    }

    public String getDieSoundEffectPath() {
        return die;
    }

    public String getCaptureSoundEffectPath() {
        return capture;
    }

    public String getRescueSoundEffectPath() {
        return rescue;
    }

    private String getFaction(UnitType unitType) {
        UnitType baseType = getBaseType(unitType);
        return CaseUtils.toSnakeCase(baseType.getFaction().name());
    }

    private String getName(UnitType unitType) {
        UnitType baseType = getBaseType(unitType);
        return CaseUtils.toSnakeCase(baseType.name());
    }

    private String getAttackType(UnitType unitType) {
        UnitType baseType = getBaseType(unitType);
        switch (baseType) {
            case TrollAxethrower: return "axe";
            case ElvenArcher: return "bow";
            case ElvenDestroyer:
            case TrollDestroyer: return "siege";
            default: return "sword";
        }
    }

    private UnitType getBaseType(UnitType unitType) {
        switch (unitType) {
            case ElvenArcherCaptive: return ElvenArcher;
            case Zuljin: return TrollAxethrower;
            default: return unitType;
        }
    }
}
