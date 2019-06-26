/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.human;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
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
public class HumanBuildingFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public HumanBuildingFactory(
        BarracksFactory barracksFactory,
        FarmFactory farmFactory,
        LumberMillFactory lumberMillFactory,
        ScoutTowerFactory scoutTowerFactory,
        TownHallFactory townHallFactory)
    {
        addProvider(UnitType.Barracks, barracksFactory);
        addProvider(UnitType.Farm, farmFactory);
        addProvider(UnitType.LumberMill, lumberMillFactory);
        addProvider(UnitType.ScoutTower, scoutTowerFactory);
        addProvider(UnitType.TownHall, townHallFactory);
    }
}
