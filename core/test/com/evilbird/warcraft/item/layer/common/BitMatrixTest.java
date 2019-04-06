/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.common;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link BitMatrix} class.
 *
 * @author Blair Butterworth
 */
public class BitMatrixTest
{
    /**
     * Matrix:
     * 0 0 0 0 0
     * 0 0 1 0 0
     * 0 1 1 1 0
     * 0 0 1 0 0
     * 0 0 0 0 0
     *
     * Expected:
     * 0 0 1
     * 0 1 1
     * 0 0 1
     */
    @Test
    public void getSubMatrixTest() {
        BitMatrix matrix = new BitMatrix(5);
        matrix.set(point(2, 1), point(1, 2), point(2, 2), point(3, 2), point(2, 3));

        BitMatrix expected = new BitMatrix(3);
        expected.set("0,0,1,0,1,1,0,0,1");

        BitMatrix actual = matrix.getSubMatrix(0, 1, 3);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Full:
     * 1 0 1
     * 0 1 1
     * 1 0 1
     *
     * Partial:
     * 0 0 1
     * 0 1 1
     * 0 0 1
     */
    @Test
    public void containsTest() {
        BitMatrix fullMatrix = new BitMatrix(3);
        fullMatrix.set(point(0, 0), point(2, 0), point(1, 1), point(2, 1), point(0, 2), point(2, 2));

        BitMatrix partialMatrix = new BitMatrix(3);
        partialMatrix.set(point(2, 0), point(1, 1), point(2, 1), point(2, 2));

        Assert.assertTrue(fullMatrix.contains(partialMatrix));
        Assert.assertFalse(partialMatrix.contains(fullMatrix));
    }

    private static GridPoint2 point(int x, int y) {
        return new GridPoint2(x, y);
    }
}
