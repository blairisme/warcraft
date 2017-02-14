package com.evilbird.engine.behaviour;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.hud.Hud;
import com.evilbird.engine.world.World;

import java.util.List;

public interface Behaviour
{
    void update(World world, Hud hud, List<UserInput> input);
}
