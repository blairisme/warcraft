/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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