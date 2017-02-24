package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.AssetObjectProviderSet;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.item.unit.human.BarracksProvider;
import com.evilbird.warcraft.item.unit.human.Farm;
import com.evilbird.warcraft.item.unit.human.Footman;
import com.evilbird.warcraft.item.unit.human.Peasant;
import com.evilbird.warcraft.item.unit.human.TownHall;
import com.evilbird.warcraft.item.unit.neutral.GoldMine;
import com.evilbird.warcraft.item.unit.neutral.Tree;
import com.evilbird.warcraft.item.unit.orc.Grunt;

import javax.inject.Inject;

public class UnitProvider extends AssetObjectProviderSet<Item>
{
    private static final Identifier FOOTMAN_ID = new Identifier("Footman");
    private static final Identifier PEASANT_ID = new Identifier("Peasant");
    private static final Identifier GRUNT_ID = new Identifier("Grunt");
    private static final Identifier GOLD_MINE_ID = new Identifier("GoldMine");
    private static final Identifier TOWN_HALL_ID = new Identifier("TownHall");
    private static final Identifier BARRACKS_ID = new Identifier("Barracks");
    private static final Identifier FARM_ID = new Identifier("Farm");
    private static final Identifier WOOD_ID = new Identifier("Wood");

    @Inject
    public UnitProvider(
        BarracksProvider barracksProvider,
        Farm farmProvider,
        Footman footmanProvider,
        Peasant peasantProvider,
        TownHall townHallProvider,
        GoldMine goldMineProvider,
        Tree treeProvider,
        Grunt gruntProvider)
    {
        super();
        addProvider(BARRACKS_ID, barracksProvider);
        addProvider(FARM_ID, farmProvider);
        addProvider(FOOTMAN_ID, footmanProvider);
        addProvider(PEASANT_ID, peasantProvider);
        addProvider(TOWN_HALL_ID, townHallProvider);
        addProvider(GOLD_MINE_ID, goldMineProvider);
        addProvider(WOOD_ID, treeProvider);
        addProvider(GRUNT_ID, gruntProvider);
    }
}
