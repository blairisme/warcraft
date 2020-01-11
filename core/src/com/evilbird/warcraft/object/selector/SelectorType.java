/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.selector;

import com.evilbird.engine.common.collection.EnumUtils;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.warcraft.object.unit.UnitType;
import org.apache.commons.lang3.Validate;

import java.util.EnumSet;

import static com.evilbird.engine.common.collection.EnumUtils.getName;

/**
 * Defines selector varieties.
 *
 * @author Blair Butterworth
 */
public enum SelectorType implements GameObjectType
{
    BarracksSelector,
    BlacksmithSelector,
    CannonTowerSelector,
    CastleSelector,
    ChurchSelector,
    FarmSelector,
    FoundrySelector,
    GnomishInventorSelector,
    GryphonAviarySelector,
    GuardTowerSelector,
    KeepSelector,
    LumberMillSelector,
    MageTowerSelector,
    OilPlatformSelector,
    RefinerySelector,
    ScoutTowerSelector,
    ShipyardSelector,
    StablesSelector,
    TownHallSelector,

    AltarOfStormsSelector,
    ForgeSelector,
    EncampmentSelector,
    BombardTowerSelector,
    DockyardSelector,
    DragonRoostSelector,
    FortressSelector,
    MetalworksSelector,
    GoblinAlchemistSelector,
    GreatHallSelector,
    LookoutTowerSelector,
    OgreMoundSelector,
    OilRefinerySelector,
    OilRigSelector,
    PigFarmSelector,
    StrongholdSelector,
    TempleOfTheDamnedSelector,
    TrollLumberMillSelector,
    WatchTowerSelector,

    BlizzardSelector,
    DeathAndDecaySelector,
    DemolitionSelector,
    FireballSelector,
    HolyVisionSelector,
    RuneTrapSelector,
    WhirlwindSelector,

    AreaSelector;

    public static EnumSet<SelectorType> areaSelectors() {
        return EnumSet.of(AreaSelector);
    }

    public static EnumSet<SelectorType> buildingSelectors() {
        return EnumSet.range(BarracksSelector, WatchTowerSelector);
    }

    public static EnumSet<SelectorType> targetSelectors() {
        return EnumSet.range(BlizzardSelector, WhirlwindSelector);
    }

    public boolean isBuildingSelector() {
        return isBuildingSelector(this);
    }

    public static boolean isBuildingSelector(SelectorType selectorType) {
        return EnumUtils.isBetween(selectorType, BarracksSelector, WatchTowerSelector);
    }

    public boolean isTargetSelector() {
        return isTargetSelector(this);
    }

    public static boolean isTargetSelector(SelectorType selectorType) {
        return EnumUtils.isBetween(selectorType, BlizzardSelector, WhirlwindSelector);
    }

    public static SelectorType forBuilding(UnitType type) {
        Validate.isTrue(type.getArchetype().isBuilding());
        return SelectorType.valueOf(type.name() + "Selector");
    }

    public static UnitType getBuilding(SelectorType selectorType) {
        Validate.isTrue(selectorType.isBuildingSelector());
        return UnitType.valueOf(getName(selectorType, "", "Selector"));
    }

    public UnitType getBuilding() {
        return getBuilding(this);
    }
}
