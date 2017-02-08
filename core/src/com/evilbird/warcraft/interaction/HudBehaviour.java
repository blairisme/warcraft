package com.evilbird.warcraft.interaction;

import com.evilbird.warcraft.device.UserInput;
import com.evilbird.warcraft.hud.Hud;
import com.evilbird.warcraft.item.Item;
import com.evilbird.warcraft.unit.World;
import com.evilbird.warcraft.utility.Identifier;

import java.util.List;

public class HudBehaviour implements Behaviour
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
