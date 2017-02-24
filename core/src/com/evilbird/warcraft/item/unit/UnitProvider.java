package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.AssetObjectProviderSet;
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
        addProvider(Units.Barracks, barracksProvider);
        addProvider(Units.Farm, farmProvider);
        addProvider(Units.Footman, footmanProvider);
        addProvider(Units.Peasant, peasantProvider);
        addProvider(Units.TownHall, townHallProvider);
        addProvider(Units.GoldMine, goldMineProvider);
        addProvider(Units.Tree, treeProvider);
        addProvider(Units.Grunt, gruntProvider);
    }
}
