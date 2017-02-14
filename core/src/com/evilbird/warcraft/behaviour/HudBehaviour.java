package com.evilbird.warcraft.behaviour;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.hud.Hud;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.engine.world.World;

import java.util.List;

public class HudBehaviour implements com.evilbird.engine.behaviour.Behaviour
{
    @Override
    public void update(World world, Hud hud, List<UserInput> inputs)
    {
        updateResourceBar(world, hud);
    }

    private void updateResourceBar(World world, Hud hud)
    {
        Item player = world.getItem(new Identifier("Player1")); //TODO
        Float gold = (Float)player.getProperty(new Identifier("Gold"));
        Float wood = (Float)player.getProperty(new Identifier("Wood"));

        Item resourceBar = hud.getItem(new Identifier("ResourceBar"));
        resourceBar.setProperty(new Identifier("Gold"), gold);
        resourceBar.setProperty(new Identifier("Wood"), wood);
    }
}
