package com.evilbird.engine.common.ai;

import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.SpatialItemNode;

/**
 * Instances of this TODO:Finish
 *
 * @author Blair Butterworth
 */
public interface SpatialGraph<T> extends IndexedGraph<T>
{
    T getNode(Vector2 position);
}
