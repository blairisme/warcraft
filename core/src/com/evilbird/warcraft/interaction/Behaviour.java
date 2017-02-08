package com.evilbird.warcraft.interaction;

import com.evilbird.warcraft.device.UserInput;
import com.evilbird.warcraft.hud.Hud;
import com.evilbird.warcraft.unit.World;

import java.util.List;

public interface Behaviour
{
    void update(World world, Hud hud, List<UserInput> input);
}
