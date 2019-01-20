/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.SpatialGraph;
import com.evilbird.engine.item.SpatialItemNode;

/**
 * @author Blair Butterworth
 */
interface MoveDestination
{
    SpatialItemNode getDestinationNode(SpatialGraph graph);

    Vector2 getOrientationTarget();

    boolean isDestinationValid();
}
