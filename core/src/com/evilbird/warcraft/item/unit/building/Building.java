package com.evilbird.warcraft.item.unit.building;

import com.evilbird.warcraft.item.unit.Unit;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Building extends Unit
{
    private float progress;
    private boolean complete;
    private boolean constructing;

    public boolean getComplete()
    {
        return complete;
    }

    public boolean getConstructing()
    {
        return constructing;
    }

    public float getProgress()
    {
        return progress;
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
