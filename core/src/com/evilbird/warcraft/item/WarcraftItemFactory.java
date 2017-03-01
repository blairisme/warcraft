package com.evilbird.warcraft.item;

import com.evilbird.engine.common.inject.AssetObjectProvider;
import com.evilbird.engine.common.inject.AssetObjectProviderSet;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemIdentifier;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.hud.HumanHudProvider;
import com.evilbird.warcraft.item.level.human.HumanLevel1;
import com.evilbird.warcraft.item.unit.UnitProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class WarcraftItemFactory implements ItemFactory
{

    private static final Identifier LEVEL_1_ID = new Identifier("Level1");
    private static final Identifier HUMAN_HUD_ID = new Identifier("HumanHud");

    private Collection<AssetObjectProviderSet<Item>> itemProviders;
    private Map<Identifier, AssetObjectProvider<ItemRoot>> groupProviders;

    @Inject
    public WarcraftItemFactory(
        UnitProvider unitProvider,
        HumanLevel1 humanLevel1Provider,
        HumanHudProvider humanHudProvider)
    {
        itemProviders = new ArrayList<AssetObjectProviderSet<Item>>();
        itemProviders.add(unitProvider);

        groupProviders = new HashMap<Identifier, AssetObjectProvider<ItemRoot>>();
        groupProviders.put(LEVEL_1_ID, humanLevel1Provider);
        groupProviders.put(HUMAN_HUD_ID, humanHudProvider);

        humanLevel1Provider.setItemFactory(this);
    }

    @Override
    public void load()
    {
        for (AssetObjectProviderSet<Item> itemProvider: itemProviders){
            itemProvider.load();
        }
        for (AssetObjectProvider<ItemRoot> groupProvider: groupProviders.values()) {
            groupProvider.load();
        }
    }

    @Override
    public Item newItem(ItemIdentifier type)
    {
        for (AssetObjectProviderSet<Item> itemProvider: itemProviders){
            if (itemProvider.contains(type)){
                return itemProvider.get(type);
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public ItemRoot newItemGroup(Identifier type)
    {
        AssetObjectProvider<ItemRoot> groupProvider = groupProviders.get(type);
        return groupProvider.get();
    }
}
