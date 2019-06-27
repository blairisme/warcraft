/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.orc;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

/**
 * Instances of this factory create Orc {@link Building Buildings}, a
 * {@link Unit} specialization that provides the user the ability to train
 * combatants and research upgrades.
 *
 * @author Blair Butterworth
 */
public class OrcBuildingFactory extends IdentifiedAssetProviderSet<Item>
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
