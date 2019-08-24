/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.math;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link GridPoints} class.
 *
 * @author Blair Butterworth
 */
public class GridPointsTest
{
    @Test
    public void zeroTest() {
        GridPoint2 zero = GridPoints.Zero;
        Assert.assertEquals(0, zero.x);
        Assert.assertEquals(0, zero.y);
    }
}