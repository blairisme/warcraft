package com.evilbird.warcraft.item.world.building;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.world.building.human.BarracksPrototypeProvider;
import com.evilbird.warcraft.item.world.building.human.BarracksProvider;
import com.evilbird.warcraft.item.world.building.human.FarmProvider;
import com.evilbird.warcraft.item.world.building.human.TownHallProvider;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BuildingProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public BuildingProvider(
        BarracksProvider barracksProvider,
        BarracksPrototypeProvider barracksPrototypeProvider,
        FarmProvider farmProvider,
        TownHallProvider townHallProvider)
    {
        super();
        addProvider(BuildingType.Barracks, barracksProvider);
        addProvider(BuildingType.BarracksPrototype, barracksPrototypeProvider);
        addProvider(BuildingType.Farm, farmProvider);
        addProvider(BuildingType.TownHall, townHallProvider);
    }
}