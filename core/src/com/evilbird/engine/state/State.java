package com.evilbird.engine.state;

import com.evilbird.engine.item.ItemRoot;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class State
{
    private ItemRoot world;
    private ItemRoot hud;

    public State(ItemRoot world, ItemRoot hud)
    {
        this.world = world;
        this.hud = hud;
    }

    public ItemRoot getWorld()
    {
        return world;
    }

    public ItemRoot getHud()
    {
        return hud;
    }
}
