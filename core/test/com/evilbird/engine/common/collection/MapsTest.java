/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this unit test validate the {@link Maps} class.
 *
 * @author Blair Butterworth
 */
public class MapsTest
{
    @Test
    public void ofOneTest() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("1", 1);

        Map<String, Integer> actual = Maps.of("1", 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void ofTwoTest() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("1", 1);
        expected.put("2", 2);

        Map<String, Integer> actual = Maps.of("1", 1, "2", 2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void ofThreeTest() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("1", 1);
        expected.put("2", 2);
        expected.put("3", 3);

        Map<String, Integer> actual = Maps.of("1", 1, "2", 2, "3", 3);
        Assert.assertEquals(expected, actual);
    }
}