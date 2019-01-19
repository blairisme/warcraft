/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.pathing;

import com.badlogic.gdx.math.GridPoint2;

public interface SpatialNode extends IndexedNode
{
    GridPoint2 getSpatialReference();
}
