package com.evilbird.engine.behaviour;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.hud.Hud;
import com.evilbird.warcraft.unit.World;

import java.util.List;

public class CompositeBehaviour implements Behaviour
{
    private List<Behaviour> behaviours;

    public CompositeBehaviour(List<Behaviour> behaviours)
    {
        this.behaviours = behaviours;
    }

    @Override
    public void update(World world, Hud hud, List<UserInput> input)
    {
        for (Behaviour behaviour: behaviours)
        {
            behaviour.update(world, hud, input);
        }
    }
}
