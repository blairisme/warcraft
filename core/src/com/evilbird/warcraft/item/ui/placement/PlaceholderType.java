/*
 * Copyright (c) 2019Placeholder, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * licensePlaceholder, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.placement;

import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.item.unit.UnitType;
import org.apache.commons.lang3.StringUtils;

/**
 * Defines placeholder varieties.
 *
 * @author Blair Butterworth
 */
public enum PlaceholderType implements ItemType
{
    BarracksPlaceholder,
    BlacksmithPlaceholder,
    CannonTowerPlaceholder,
    CastlePlaceholder,
    ChurchPlaceholder,
    FarmPlaceholder,
    FoundryPlaceholder,
    GnomishInventorPlaceholder,
    GryphonAviaryPlaceholder,
    GuardTowerPlaceholder,
    KeepPlaceholder,
    LumberMillPlaceholder,
    MageTowerPlaceholder,
    OilPlatformPlaceholder,
    RefineryPlaceholder,
    ScoutTowerPlaceholder,
    ShipyardPlaceholder,
    StablesPlaceholder,
    TownHallPlaceholder,

    AltarOfStormsPlaceholder,
    ForgePlaceholder,
    EncampmentPlaceholder,
    BombardTowerPlaceholder,
    DockyardPlaceholder,
    DragonRoostPlaceholder,
    FortressPlaceholder,
    MetalworksPlaceholder,
    GoblinAlchemistPlaceholder,
    GreatHallPlaceholder,
    LookoutTowerPlaceholder,
    OgreMoundPlaceholder,
    OilRefineryPlaceholder,
    OilRigPlaceholder,
    PigFarmPlaceholder,
    StrongholdPlaceholder,
    TempleOfTheDamnedPlaceholder,
    TrollLumberMillPlaceholder,
    WatchTowerPlaceholder;

    public UnitType getBuilding() {
        return UnitType.valueOf(getBuildingName());
    }

    private String getBuildingName() {
        return StringUtils.remove(name(), "Placeholder");
    }
}
