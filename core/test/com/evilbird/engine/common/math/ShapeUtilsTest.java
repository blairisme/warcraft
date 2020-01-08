/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.math;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link ShapeUtils} class.
 *
 * @author Blair Butterworth
 */
public class ShapeUtilsTest
{
    @Test
    public void containsTest() {
        Circle circle = new Circle(10, 10, 5);
        Rectangle rectangle = new Rectangle(0, 0, 7, 7);
        Assert.assertTrue(ShapeUtils.contains(circle, rectangle));
    }

    @Test
    public void notContainsTest() {
        Circle circle = new Circle(10, 10, 5);
        Rectangle rectangle = new Rectangle(0, 0, 4, 4);
        Assert.assertFalse(ShapeUtils.contains(circle, rectangle));
    }

    @Test (expected = NullPointerException.class)
    public void nullCircleTest() {
        Rectangle rectangle = new Rectangle(0, 0, 4, 4);
        ShapeUtils.contains(null, rectangle);
    }

    @Test (expected = NullPointerException.class)
    public void nullRectangleTest() {
        Circle circle = new Circle(10, 10, 5);
        ShapeUtils.contains(circle, null);
    }
}