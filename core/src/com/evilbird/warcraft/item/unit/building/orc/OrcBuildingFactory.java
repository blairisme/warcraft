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
        EncampmentFactory encampmentFactory,
        GreatHallFactory greatHallFactory,
        PigFarmFactory pigFarmFactory,
        TrollLumberMillFactory trollLumberMillFactory,
        WatchTowerFactory watchTowerFactory)
    {
        super();
        addProvider(UnitType.Encampment, encampmentFactory);
        addProvider(UnitType.GreatHall, greatHallFactory);
        addProvider(UnitType.PigFarm, pigFarmFactory);
        addProvider(UnitType.TrollLumberMill, trollLumberMillFactory);
        addProvider(UnitType.WatchTower, watchTowerFactory);
    }
}
