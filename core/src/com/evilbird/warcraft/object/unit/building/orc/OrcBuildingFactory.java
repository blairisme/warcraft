/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.orc;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;

import javax.inject.Inject;

/**
 * Instances of this factory create Orc {@link Building Buildings}, a
 * {@link Unit} specialization that provides the user the ability to train
 * combatants and research upgrades.
 *
 * @author Blair Butterworth
 */
public class OrcBuildingFactory extends GameFactorySet<Building>
{
    @Inject
    public OrcBuildingFactory(
        AltarOfStormsFactory altarOfStormsFactory,
        BombardTowerFactory bombardTowerFactory,
        DockyardFactory dockyardFactory,
        DragonRoostFactory dragonRoostFactory,
        EncampmentFactory encampmentFactory,
        ForgeFactory forgeFactory,
        FortressFactory fortressFactory,
        GoblinAlchemistFactory goblinAlchemistFactory,
        GreatHallFactory greatHallFactory,
        LookoutTowerFactory lookoutTowerFactory,
        MetalworksFactory metalworksFactory,
        OgreMoundFactory ogreMoundFactory,
        OilRefineryFactory oilRefineryFactory,
        OilRigFactory oilRigFactory,
        PigFarmFactory pigFarmFactory,
        StrongholdFactory strongholdFactory,
        TempleOfTheDamnedFactory templeOfTheDamnedFactory,
        TrollLumberMillFactory trollLumberMillFactory,
        WatchTowerFactory watchTowerFactory)
    {
        addProvider(UnitType.AltarOfStorms, altarOfStormsFactory);
        addProvider(UnitType.BombardTower, bombardTowerFactory);
        addProvider(UnitType.Dockyard, dockyardFactory);
        addProvider(UnitType.DragonRoost, dragonRoostFactory);
        addProvider(UnitType.Encampment, encampmentFactory);
        addProvider(UnitType.Forge, forgeFactory);
        addProvider(UnitType.Fortress, fortressFactory);
        addProvider(UnitType.GoblinAlchemist, goblinAlchemistFactory);
        addProvider(UnitType.GreatHall, greatHallFactory);
        addProvider(UnitType.LookoutTower, lookoutTowerFactory);
        addProvider(UnitType.Metalworks, metalworksFactory);
        addProvider(UnitType.OgreMound, ogreMoundFactory);
        addProvider(UnitType.OilRefinery, oilRefineryFactory);
        addProvider(UnitType.OilRig, oilRigFactory);
        addProvider(UnitType.PigFarm, pigFarmFactory);
        addProvider(UnitType.Stronghold, strongholdFactory);
        addProvider(UnitType.TempleOfTheDamned, templeOfTheDamnedFactory);
        addProvider(UnitType.TrollLumberMill, trollLumberMillFactory);
        addProvider(UnitType.WatchTower, watchTowerFactory);
    }
}
