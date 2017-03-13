package com.evilbird.warcraft.item.data.player;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.ItemGroup;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Player extends ItemGroup
{
    private float gold;
    private float oil;
    private float wood;

    @Inject
    public Player()
    {
        super.setPosition(0, 0);
        super.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    public boolean isConsoleUser()
    {
        return true;
    }

    @Override
    public void setSize(Vector2 size)
    {
        super.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    @Override
    public void setSize(float width, float height)
    {
        super.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    @Override
    public void setPosition(Vector2 position)
    {
        super.setPosition(0, 0);
    }

    @Override
    public void setPosition(float x, float y)
    {
        super.setPosition(0, 0);
    }
}
