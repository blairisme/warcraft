/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.human.BarracksFactory;
import com.evilbird.warcraft.item.unit.building.human.FarmFactory;
import com.evilbird.warcraft.item.unit.building.human.LumberMillFactory;
import com.evilbird.warcraft.item.unit.building.human.TownHallFactory;
import com.evilbird.warcraft.item.unit.building.neutral.CircleOfPowerFactory;
import com.evilbird.warcraft.item.unit.building.orc.WatchTowerFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Building Buildings}, a {@link Unit}
 * specialization that provides the user the ability to train
 * {@link Unit Units}.
 *
 * @author Blair Butterworth
 */
public class BuildingFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public BuildingFactory(
        BarracksFactory barracksFactory,
        CircleOfPowerFactory circleOfPowerFactory,
        FarmFactory farmFactory,
        LumberMillFactory lumberMillFactory,
        TownHallFactory townHallFactory,
        WatchTowerFactory watchTowerFactory)
    {
        super();
        addProvider(UnitType.Barracks, barracksFactory);
        addProvider(UnitType.CircleOfPower, circleOfPowerFactory);
        addProvider(UnitType.Farm, farmFactory);
        addProvider(UnitType.LumberMill, lumberMillFactory);
        addProvider(UnitType.TownHall, townHallFactory);
        addProvider(UnitType.WatchTower, watchTowerFactory);
    }
}