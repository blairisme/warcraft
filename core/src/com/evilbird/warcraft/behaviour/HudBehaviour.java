package com.evilbird.warcraft.behaviour;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.utility.Identifier;

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
        Item player = world.getItem(new Identifier("Player1")); //TODO
        Float gold = (Float)player.getProperty(new Identifier("Gold"));
        Float wood = (Float)player.getProperty(new Identifier("Wood"));

        Item resourceBar = hud.getItem(new Identifier("ResourceBar"));
        resourceBar.setProperty(new Identifier("Gold"), gold);
        resourceBar.setProperty(new Identifier("Wood"), wood);
    }
}
