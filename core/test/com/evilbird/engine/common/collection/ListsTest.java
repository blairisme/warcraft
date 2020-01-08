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

import java.util.Arrays;
import java.util.List;

/**
 * Instances of this unit test validate the {@link Lists} class.
 *
 * @author Blair Butterworth
 */
public class ListsTest
{
    @Test
    public void unionCollectionTest() {
        List<String> collectionA = Arrays.asList("1", "2");
        List<String> collectionB = Arrays.asList("3", "4");
        List<String> expected = Arrays.asList("1", "2", "3", "4");
        List<String> actual = Lists.union(collectionA, collectionB);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void unionElementTest() {
        String element = "1";
        List<String> collection = Arrays.asList("2", "3");
        List<String> expected = Arrays.asList("1", "2", "3");
        List<String> actual = Lists.union(element, collection);
        Assert.assertEquals(expected, actual);
    }
}