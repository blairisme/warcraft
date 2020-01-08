/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.pathing;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Instances of this class contain common {@link SpatialNode} utility functions.
 *
 * @author Blair Butterworth
 */
public class SpatialUtils
{
    private SpatialUtils() {
    }

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
            float distanceA = nodeA.getSpatialReference().dst2(target);
            float distanceB = nodeB.getSpatialReference().dst2(target);
            return Float.compare(distanceA, distanceB);
        }
    }
}
