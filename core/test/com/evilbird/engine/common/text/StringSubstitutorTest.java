/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.text;

import com.evilbird.engine.common.collection.Maps;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link StringSubstitutor} class.
 *
 * @author Blair Butterworth
 */
public class StringSubstitutorTest
{
    @Test
    public void replaceTest() {
        StringSubstitutor substitutor = new StringSubstitutor(Maps.of("foo", "has"));
        String expected = "this has been replaced";
        String actual = substitutor.replace("this ${foo} been replaced");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void replaceMulitpleTest() {
        StringSubstitutor substitutor = new StringSubstitutor(Maps.of("foo", "has", "bar", "so", "waz", "this"));
        String expected = "this has been replaced and so has this";
        String actual = substitutor.replace("this ${foo} been replaced and ${bar} has ${waz}");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void replaceNothingTest() {
        StringSubstitutor substitutor = new StringSubstitutor(Maps.of("bob", "wang"));
        String expected = "this ${foo} been replaced";
        String actual = substitutor.replace("this ${foo} been replaced");
        Assert.assertEquals(expected, actual);
    }
}