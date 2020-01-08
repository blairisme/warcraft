/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

    @Test
    public void getAllTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        Collection<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(3);

        Collection<Integer> actual = Maps.getAll(map, Arrays.asList("1", "3"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getOrDefaultTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        Assert.assertEquals(0, (int)Maps.getOrDefaultSupplied(map, "0", () -> 0));
        Assert.assertEquals(1, (int)Maps.getOrDefaultSupplied(map, "1", () -> 0));
    }
}