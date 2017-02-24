package com.evilbird.warcraft.behaviour;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemUtils;
import com.evilbird.engine.utility.Identifier;

import java.util.Collection;
import java.util.List;

public class HudBehaviour implements Behaviour
{
    @Override
    public void update(ItemGroup world, ItemGroup hud, List<UserInput> inputs)
    {
        updateResourceBar(world, hud);
    }

    private void updateResourceBar(ItemGroup world, ItemGroup hud)
    {
        Item player = world.getItem(new Identifier("Player1")); //TODO: Obtain Id + Cache
        Float gold = (Float)player.getProperty(new Identifier("Gold"));
        Float wood = (Float)player.getProperty(new Identifier("Wood"));

        Item resourceBar = hud.getItem(new Identifier("ResourcePanel")); //TODO: Cache
        resourceBar.setProperty(new Identifier("Gold"), gold);
        resourceBar.setProperty(new Identifier("Wood"), wood);

        Collection<Item> selection = ItemUtils.findAll(world.getItems(), new Identifier("Selected"), true);

        Item actionPanel = hud.getItem(new Identifier("ActionPanel")); //TODO: Cache
        actionPanel.setProperty(new Identifier("Selection"), selection);

        Item selectionPanel = hud.getItem(new Identifier("SelectionPanel")); //TODO: Cache
        selectionPanel.setProperty(new Identifier("Selection"), selection);



    }
}
