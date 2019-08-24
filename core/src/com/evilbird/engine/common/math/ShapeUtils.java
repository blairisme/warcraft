/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.math;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Instances of this class contain common utility functions for working with
 * shapes.
 *
 * @author Blair Butterworth
 */
public class ShapeUtils
{
    private ShapeUtils() {
    }

    /**
     * Determines if the given {@link Circle} contains any portion of the given
     * {@link Rectangle}.
     *
     * @param circle    the {@code Circle} to test.
     * @param rectangle an intersecting {@code Rectangle}.
     * @return          {@code true} if the given {@code Rectangle} intersects
     *                  the {@code Circle}.
     *
     * @throws NullPointerException thrown if the given {@code Circle} or
     *                              {@code Rectangle} is {@code null}.
     */
    public static boolean contains(Circle circle, Rectangle rectangle) {
        float top = rectangle.y + rectangle.height;
        float right = rectangle.x + rectangle.width;
        return circle.contains(rectangle.x, rectangle.y)
            || circle.contains(rectangle.x, top)
            || circle.contains(right, rectangle.y)
            || circle.contains(right, top);
    }
}
