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
import com.evilbird.engine.common.collection.Collections;
import com.evilbird.engine.common.function.Comparator;

import java.util.Collection;

/**
 * Instances of this class contain common {@link SpatialNode} utility functions.
 *
 * @author Blair Butterworth
 */
//TODO: consider using dst2
public class SpatialUtils
{
    public static <T extends SpatialNode> T getClosest(Collection<T> nodes, T target) {
        return Collections.min(nodes, new SpatialNodeDistanceComparator(target));
    }

    private static class SpatialNodeDistanceComparator implements Comparator<SpatialNode> {
        private GridPoint2 target;

        public SpatialNodeDistanceComparator(SpatialNode target) {
            this.target = target.getSpatialReference();
        }

        @Override
        public int compare(SpatialNode nodeA, SpatialNode nodeB) {
            float distanceA = nodeA.getSpatialReference().dst(target);
            float distanceB = nodeB.getSpatialReference().dst(target);
            return Float.compare(distanceA, distanceB);
        }
    }
}
