package com.evilbird.warcraft.item.world.building;

import com.evilbird.warcraft.item.world.WorldItem;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Building extends WorldItem
{
    private float progress;
    private boolean complete;
    private boolean constructing;

    public float getProgress()
    {
        return progress;
    }

    public boolean getComplete()
    {
        return complete;
    }

    public boolean getConstructing()
    {
        return constructing;
    }

    public void setComplete(boolean complete)
    {
        this.complete = complete;
    }

    public void setConstructing(boolean constructing)
    {
        this.constructing = constructing;
    }

    public void setProgress(float progress)
    {
        this.progress = progress;
    }
}
