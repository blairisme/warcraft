package com.evilbird.engine.item;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.ai.SpatialNode;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SpatialItemNode implements SpatialNode
{
    private int index;
    private Item occupant;
    private GridPoint2 gridReference;

    public SpatialItemNode(int index, GridPoint2 gridReference)
    {
        this.index = index;
        this.occupant = null;
        this.gridReference = gridReference;
    }

    @Override
    public int getIndex()
    {
        return index;
    }

    @Override
    public GridPoint2 getSpatialReference()
    {
        return gridReference;
    }

    public Item getOccupant()
    {
        return occupant;
    }

    public void setOccupant(Item occupant)
    {
        this.occupant = occupant;
    }
}
