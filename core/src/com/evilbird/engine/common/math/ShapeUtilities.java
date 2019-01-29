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
public class ShapeUtilities
{
    private ShapeUtilities() {
    }

    public static boolean contains(Circle circle, Rectangle rectangle) {
        if (circle.contains(rectangle.x, rectangle.y)){
            return true;
        }
        float top = rectangle.y + rectangle.height;

        if (circle.contains(rectangle.x, top)){
            return true;
        }
        float right = rectangle.x + rectangle.width;

        if (circle.contains(right, rectangle.y)){
            return true;
        }
        if (circle.contains(right, top)){
            return true;
        }
        return false;
    }
}
