/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.math;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Comparator;

/**
 * Provides utility functions that operate on {@link GridPoint2 GridPoint2s}.
 *
 * @author Blair Butterworth
 */
public class GridPointUtils
{
    public static final GridPoint2 Zero = new GridPoint2(0, 0);

    public static int compare(GridPoint2 target, GridPoint2 point1, GridPoint2 point2) {
        float distance1 = target.dst2(point1);
        float distance2 = target.dst2(point2);
        return Float.compare(distance1, distance2);
    }

    public static Comparator<GridPoint2> comparator(GridPoint2 target) {
        return (point1, point2) -> compare(target, point1, point2);
    }

    private GridPointUtils() {
    }
}
