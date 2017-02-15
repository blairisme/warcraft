package com.evilbird.engine.behaviour;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.ItemGroup;

import java.util.List;

public interface Behaviour
{
    void update(ItemGroup world, ItemGroup hud, List<UserInput> input);
}
