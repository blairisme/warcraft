/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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