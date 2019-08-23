/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.math;

import com.badlogic.gdx.math.Vector2;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link VectorUtils} class.
 *
 * @author Blair Butterworth
 */
public class VectorUtilsTest
{
    @Test
    public void clampMinTest() {
        Vector2 min = new Vector2(0, 0);
        Vector2 max = new Vector2(10, 10);

        Vector2 value = new Vector2(-7, -7);
        Vector2 expected = new Vector2(0, 0);
        Vector2 actual = VectorUtils.clamp(value, min, max);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void clampMaxTest() {
        Vector2 min = new Vector2(0, 0);
        Vector2 max = new Vector2(10, 10);

        Vector2 value = new Vector2(17, 17);
        Vector2 expected = new Vector2(10, 10);
        Vector2 actual = VectorUtils.clamp(value, min, max);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void clampBothTest() {
        Vector2 min = new Vector2(0, 0);
        Vector2 max = new Vector2(10, 10);

        Vector2 value = new Vector2(-7, 17);
        Vector2 expected = new Vector2(0, 10);
        Vector2 actual = VectorUtils.clamp(value, min, max);
        Assert.assertEquals(expected, actual);
    }

    @Test (expected = NullPointerException.class)
    public void clampNullValueTest() {
        Vector2 min = new Vector2(0, 0);
        Vector2 max = new Vector2(10, 10);
        VectorUtils.clamp(null, min, max);
    }

    @Test (expected = NullPointerException.class)
    public void clampNullMinTest() {
        Vector2 max = new Vector2(10, 10);
        Vector2 value = new Vector2(-7, -7);
        VectorUtils.clamp(value, null, max);
    }

    @Test (expected = NullPointerException.class)
    public void clampNullMaxTest() {
        Vector2 min = new Vector2(0, 0);
        Vector2 value = new Vector2(-7, -7);
        VectorUtils.clamp(value, min, null);
    }

    @Test
    public void multiplyTest() {
        Vector2 value = new Vector2(2, 2);
        Vector2 expected = new Vector2(4, 4);
        Vector2 actual = VectorUtils.multiply(value, 2);
        Assert.assertEquals(expected, actual);
    }

    @Test (expected = NullPointerException.class)
    public void multiplyNullTest() {
        VectorUtils.multiply(null, 2);
    }

    @Test
    public void subtractTest() {
        Vector2 value = new Vector2(6, 6);
        Vector2 delta = new Vector2(2, 2);
        Vector2 expected = new Vector2(4, 4);
        Vector2 actual = VectorUtils.subtract(value, delta);
        Assert.assertEquals(expected, actual);
    }

    @Test (expected = NullPointerException.class)
    public void subtractNullValueTest() {
        Vector2 delta = new Vector2(2, 2);
        VectorUtils.subtract(null, delta);
    }

    @Test (expected = NullPointerException.class)
    public void subtractNullDeltaTest() {
        Vector2 value = new Vector2(6, 6);
        VectorUtils.subtract(value, null);
    }

    @Test
    public void roundDownTest() {
        Vector2 value = new Vector2(66, 66);
        Vector2 multiple = new Vector2(32, 32);
        Vector2 expected = new Vector2(64, 64);
        Vector2 actual = VectorUtils.round(value, multiple);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void roundUpTest() {
        Vector2 value = new Vector2(80, 80);
        Vector2 multiple = new Vector2(32, 32);
        Vector2 expected = new Vector2(96, 96);
        Vector2 actual = VectorUtils.round(value, multiple);
        Assert.assertEquals(expected, actual);
    }

    @Test (expected = NullPointerException.class)
    public void roundNullValueTest() {
        Vector2 multiple = new Vector2(32, 32);
        VectorUtils.round(null, multiple);
    }

    @Test (expected = NullPointerException.class)
    public void roundNullMultipleTest() {
        Vector2 value = new Vector2(80, 80);
        VectorUtils.round(value, null);
    }
}