package com.evilbird.warcraft.item.data.player;

import com.evilbird.warcraft.item.world.unit.Unit;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Player extends Unit
{
    private float gold;
    private float oil;
    private float wood;

    @Inject
    public Player()
    {
    }

    @Override
    public float getGold()
    {
        return gold;
    }

    @Override
    public float getOil()
    {
        return oil;
    }

    @Override
    public float getWood()
    {
        return wood;
    }

    @Override
    public void setGold(float gold)
    {
        this.gold = gold;
    }

    @Override
    public void setOil(float oil)
    {
        this.oil = oil;
    }

    @Override
    public void setWood(float wood)
    {
        this.wood = wood;
    }
}
