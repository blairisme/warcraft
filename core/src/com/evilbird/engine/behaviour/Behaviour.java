package com.evilbird.engine.behaviour;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.ItemRoot;

import java.util.List;

public interface Behaviour
{
    void update(ItemRoot world, ItemRoot hud, List<UserInput> input);
}
