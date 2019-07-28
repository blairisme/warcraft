/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.human;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

/**
 * Instances of this factory create Human {@link Building Buildings}, a
 * {@link Unit} specialization that provides the user the ability to train
 * combatants and research upgrades.
 *
 * @author Blair Butterworth
 */
public class HumanBuildingFactory extends GameFactorySet<Building>
{
    @Inject
    public HumanBuildingFactory(
        BarracksFactory barracksFactory,
        BlacksmithFactory blacksmithFactory,
        CannonTowerFactory cannonTowerFactory,
        CastleFactory castleFactory,
        ChurchFactory churchFactory,
        FarmFactory farmFactory,
        FoundryFactory foundryFactory,
        GnomishInventorFactory gnomishInventorFactory,
        GryphonAviaryFactory gryphonAviaryFactory,
        GuardTowerFactory guardTowerFactory,
        KeepFactory keepFactory,
        LumberMillFactory lumberMillFactory,
        MageTowerFactory mageTowerFactory,
        OilPlatformFactory oilPlatformFactory,
        RefineryFactory refineryFactory,
        ScoutTowerFactory scoutTowerFactory,
        ShipyardFactory shipyardFactory,
        StablesFactory stablesFactory,
        TownHallFactory townHallFactory)
    {
        addProvider(UnitType.Barracks, barracksFactory);
        addProvider(UnitType.Blacksmith, blacksmithFactory);
        addProvider(UnitType.CannonTower, cannonTowerFactory);
        addProvider(UnitType.Castle, castleFactory);
        addProvider(UnitType.Church, churchFactory);
        addProvider(UnitType.Farm, farmFactory);
        addProvider(UnitType.Foundry, foundryFactory);
        addProvider(UnitType.GnomishInventor, gnomishInventorFactory);
        addProvider(UnitType.GryphonAviary, gryphonAviaryFactory);
        addProvider(UnitType.GuardTower, guardTowerFactory);
        addProvider(UnitType.Keep, keepFactory);
        addProvider(UnitType.LumberMill, lumberMillFactory);
        addProvider(UnitType.MageTower, mageTowerFactory);
        addProvider(UnitType.OilPlatform, oilPlatformFactory);
        addProvider(UnitType.Refinery, refineryFactory);
        addProvider(UnitType.ScoutTower, scoutTowerFactory);
        addProvider(UnitType.Shipyard, shipyardFactory);
        addProvider(UnitType.Stables, stablesFactory);
        addProvider(UnitType.TownHall, townHallFactory);
    }
}
