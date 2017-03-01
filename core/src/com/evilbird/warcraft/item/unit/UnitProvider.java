package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.inject.AssetObjectProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.human.BarracksProvider;
import com.evilbird.warcraft.item.unit.human.FarmProvider;
import com.evilbird.warcraft.item.unit.human.FootmanProvider;
import com.evilbird.warcraft.item.unit.human.PeasantProvider;
import com.evilbird.warcraft.item.unit.human.TownHallProvider;
import com.evilbird.warcraft.item.unit.neutral.GoldMineProvider;
import com.evilbird.warcraft.item.unit.neutral.TreeProvider;
import com.evilbird.warcraft.item.unit.orc.GruntProvider;

import javax.inject.Inject;

public class UnitProvider extends AssetObjectProviderSet<Item>
{
    @Inject
    public UnitProvider(
        BarracksProvider barracksProvider,
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
        addProvider(UnitType.Farm, farmProvider);
        addProvider(UnitType.Footman, footmanProvider);
        addProvider(UnitType.Peasant, peasantProvider);
        addProvider(UnitType.TownHall, townHallProvider);
        addProvider(UnitType.GoldMine, goldMineProvider);
        addProvider(UnitType.Tree, treeProvider);
        addProvider(UnitType.Grunt, gruntProvider);
    }
}
