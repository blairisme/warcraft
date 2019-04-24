/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.human.BarracksFactory;
import com.evilbird.warcraft.item.unit.building.human.FarmFactory;
import com.evilbird.warcraft.item.unit.building.human.TownHallFactory;

import javax.inject.Inject;

public class BuildingFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public BuildingFactory(
        BarracksFactory barracksFactory,
        FarmFactory farmFactory,
        TownHallFactory townHallFactory)
    {
        super();
        addProvider(UnitType.Barracks, barracksFactory);
        addProvider(UnitType.Farm, farmFactory);
        addProvider(UnitType.TownHall, townHallFactory);
    }
}