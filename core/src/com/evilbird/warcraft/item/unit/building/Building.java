package com.evilbird.warcraft.item.unit.building;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.unit.Unit;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Building extends Unit
{
    private boolean constructing;
    private boolean producing;
    private float progress;
    private Identifier productionType;

    public Building()
    {
        constructing = false;
        producing = false;
        progress = 1f;
    }

    public boolean isConstructing()
    {
        return constructing;
    }

    public boolean isProducing()
    {
        return producing;
    }

    public float getProgress()
    {
        return progress;
    }

    public void setConstructing(boolean constructing)
    {
        this.constructing = constructing;
    }

    public void setProducing(boolean producing)
    {
        this.producing = producing;
    }

    public void setProgress(float progress)
    {
        this.progress = progress;
    }
}
