/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.collection;

import com.evilbird.test.verifier.EqualityVerifier;
import org.junit.Assert;
import org.junit.Test;

import static com.evilbird.engine.common.collection.BitMatrix.matrix3;

/**
 * Instances of this unit test validate the {@link BitMatrix} class.
 *
 * @author Blair Butterworth
 */
public class BitMatrixTest
{
    @Test
    public void getTest() {
        BitMatrix matrix = matrix3(
                "1,0,1," +
                "0,1,1," +
                "0,0,1");

        Assert.assertTrue(matrix.get(0, 0));
        Assert.assertTrue(matrix.get(2, 2));
        Assert.assertFalse(matrix.get(0, 1));
        Assert.assertFalse(matrix.get(1, 2));
    }

    @Test
    public void isEmptyTest() {
        BitMatrix matrix = new BitMatrix(2);
        Assert.assertTrue(matrix.isEmpty());

        matrix.set("0,0,1,1");
        Assert.assertFalse(matrix.isEmpty());

        matrix.set(0, 1, false);
        Assert.assertFalse(matrix.isEmpty());

        matrix.set(1, 1, false);
        Assert.assertTrue(matrix.isEmpty());
    }

    @Test
    public void setBitsTest() {
        BitMatrix matrix = new BitMatrix(2);
        matrix.set("0,0,1,1");

        Assert.assertFalse(matrix.get(0,0));
        Assert.assertFalse(matrix.get(1,0));

        Assert.assertTrue(matrix.get(0,1));
        Assert.assertTrue(matrix.get(1,1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void setBitsEmptyTest() {
        BitMatrix matrix = new BitMatrix(2);
        matrix.set("");
    }

    @Test (expected = NullPointerException.class)
    public void setBitsNullTest() {
        BitMatrix matrix = new BitMatrix(2);
        matrix.set(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setBitsInvalidTest() {
        BitMatrix matrix = new BitMatrix(2);
        matrix.set("0,0,a,b");
    }

    @Test (expected = IllegalArgumentException.class)
    public void setBitsSpaceTest() {
        BitMatrix matrix = new BitMatrix(2);
        matrix.set("0, 0, 1, 1");
    }

    @Test
    public void setsTest() {
        BitMatrix matrix = new BitMatrix(2);
        Assert.assertFalse(matrix.get(0,0));
        matrix.set(0, 0, true);
        Assert.assertTrue(matrix.get(0,0));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void setInvalidXTest() {
        BitMatrix matrix = new BitMatrix(2);
        matrix.set(55, 0, true);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void setInvalidYTest() {
        BitMatrix matrix = new BitMatrix(2);
        matrix.set(0, -4, true);
    }

    @Test
    public void subMatrixTest() {
        BitMatrix matrix = new BitMatrix(5);
        matrix.set( "0,0,0,0,0," +
                    "1,0,1,0,0," +
                    "0,1,1,1,0," +
                    "0,0,1,0,0," +
                    "0,0,0,0,0");

        BitMatrix expected = new BitMatrix(3);
        expected.set("1,0,1," +
                     "0,1,1," +
                     "0,0,1");

        BitMatrix actual = matrix.subMatrix(0, 1, 3);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void subMatrixFullTest() {
        BitMatrix matrix = new BitMatrix(5);
        matrix.set( "0,0,0,0,0," +
                "0,0,1,0,0," +
                "0,1,1,1,0," +
                "0,0,1,0,0," +
                "0,0,0,0,0");

        BitMatrix actual = matrix.subMatrix(0, 0, 5);
        Assert.assertEquals(matrix, actual);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void subMatrixInvalidXTest() {
        BitMatrix matrix = new BitMatrix(5);
        matrix.set("0,0,0,0,0," +
                "0,0,1,0,0," +
                "0,1,1,1,0," +
                "0,0,1,0,0," +
                "0,0,0,0,0");

        matrix.subMatrix(55, 0, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subMatrixInvalidYTest() {
        BitMatrix matrix = new BitMatrix(5);
        matrix.set("0,0,0,0,0," +
                "0,0,1,0,0," +
                "0,1,1,1,0," +
                "0,0,1,0,0," +
                "0,0,0,0,0");

        matrix.subMatrix(0, -4, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subMatrixOutOfBoundsXTest() {
        BitMatrix matrix = new BitMatrix(5);
        matrix.set("0,0,0,0,0," +
                "0,0,1,0,0," +
                "0,1,1,1,0," +
                "0,0,1,0,0," +
                "0,0,0,0,0");

        matrix.subMatrix(1, 0, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void subMatrixOutOfBoundsYTest() {
        BitMatrix matrix = new BitMatrix(5);
        matrix.set("0,0,0,0,0," +
                "0,0,1,0,0," +
                "0,1,1,1,0," +
                "0,0,1,0,0," +
                "0,0,0,0,0");

        matrix.subMatrix(0, 1, 5);
    }

    @Test
    public void equalsTest() {
        BitMatrix actual = new BitMatrix(2);
        actual.set("0,0,1,0");
        actual.set(1, 1, true);

        BitMatrix expected = new BitMatrix(2);
        expected.set("0,0,1,1");

        Assert.assertEquals(expected, actual);
        actual.set(1, 1, false);
        Assert.assertNotEquals(expected, actual);
    }

    @Test
    public void equalityTest() {
        EqualityVerifier.forClass(BitMatrix.class)
            .excludeFields("size")
            .excludeFields("label")
            .excludeFields("bits")
            .excludeFields("id")
            .verify();
    }

    @Test
    public void toStringTest() {
        BitMatrix matrix = new BitMatrix(2);
        matrix.set("0,0,1,0");
        matrix.set(1, 1, true);

        String expected = "0011";
        String actual = matrix.toString();

        Assert.assertEquals(expected, actual);
    }
}
