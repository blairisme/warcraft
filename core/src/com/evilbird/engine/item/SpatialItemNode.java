package com.evilbird.engine.item;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.ai.SpatialNode;
import com.evilbird.engine.common.lang.Identifier;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SpatialItemNode implements SpatialNode
{
    private int index;
    private GridPoint2 spatialIndex;
    private Identifier occupant;

    public SpatialItemNode(int index, GridPoint2 spatialIndex, Identifier occupant)
    {
        this.spatialIndex = spatialIndex;
        this.index = index;
        this.occupant = occupant;
    }

    @Override
    public int getIndex()
    {
        return index;
    }

    @Override
    public GridPoint2 getSpatialIndex()
    {
        return spatialIndex;
    }

    public Identifier getOccupant()
    {
        return occupant;
    }
}
