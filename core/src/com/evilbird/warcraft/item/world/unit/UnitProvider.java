package com.evilbird.warcraft.item.world.unit;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.world.building.human.BarracksPrototypeProvider;
import com.evilbird.warcraft.item.world.building.human.BarracksProvider;
import com.evilbird.warcraft.item.world.building.human.FarmProvider;
import com.evilbird.warcraft.item.world.building.human.TownHallProvider;
import com.evilbird.warcraft.item.world.resource.GoldMineProvider;
import com.evilbird.warcraft.item.world.resource.TreeProvider;
import com.evilbird.warcraft.item.world.unit.human.FootmanProvider;
import com.evilbird.warcraft.item.world.unit.human.PeasantProvider;
import com.evilbird.warcraft.item.world.unit.orc.GruntProvider;

import javax.inject.Inject;

public class UnitProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public UnitProvider(
        BarracksProvider barracksProvider,
        BarracksPrototypeProvider barracksPrototypeProvider,
        FarmProvider farmProvider,
        FootmanProvider footmanProvider,
        PeasantProvider peasantProvider,
        TownHallProvider townHallProvider,
        GoldMineProvider goldMineProvider,
        TreeProvider treeProvider,
        GruntProvider gruntProvider)
    {
        super();
        addProvider(UnitType.Barracks, barracksProvider);
        addProvider(UnitType.BarracksPrototype, barracksPrototypeProvider);
        addProvider(UnitType.Farm, farmProvider);
        addProvider(UnitType.Footman, footmanProvider);
        addProvider(UnitType.Peasant, peasantProvider);
        addProvider(UnitType.TownHall, townHallProvider);
        addProvider(UnitType.GoldMine, goldMineProvider);
        addProvider(UnitType.Tree, treeProvider);
        addProvider(UnitType.Grunt, gruntProvider);
    }
}
