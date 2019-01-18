package com.evilbird.engine.common.ai;

import com.badlogic.gdx.math.GridPoint2;

public interface SpatialNode extends IndexedNode
{
    GridPoint2 getSpatialReference();
}
