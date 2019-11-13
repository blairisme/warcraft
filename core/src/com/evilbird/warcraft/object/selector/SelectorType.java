/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
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

    public static boolean isBuildingSelector(SelectorType selectorType) {
        return EnumUtils.isBetween(selectorType, BarracksSelector, WatchTowerSelector);
    }

    public static boolean isTargetSelector(SelectorType selectorType) {
        return EnumUtils.isBetween(selectorType, BlizzardSelector, WhirlwindSelector);
    }

    public static SelectorType forBuilding(UnitType unitType) {
        Validate.isTrue(unitType.isBuilding());
        return SelectorType.valueOf(unitType.name() + "Selector");
    }

    public static UnitType getBuilding(SelectorType selectorType) {
        Validate.isTrue(selectorType.isBuildingSelector());
        return UnitType.valueOf(getName(selectorType, "", "Selector"));
    }

    public UnitType getBuilding() {
        return getBuilding(this);
    }

    public boolean isBuildingSelector() {
        return isBuildingSelector(this);
    }

    public boolean isTargetSelector() {
        return isTargetSelector(this);
    }
}
