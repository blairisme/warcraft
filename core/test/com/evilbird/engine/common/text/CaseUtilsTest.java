/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.text;

import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link CaseUtils} class.
 *
 * @author Blair Butterworth
 */
public class CaseUtilsTest
{
    @Test
    public void toSnakeCaseTest() {
        String expected = "elven_archer";
        String actual = CaseUtils.toSnakeCase("ElvenArcher");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void oneWordUpperTest() {
        String expected = "elven";
        String actual = CaseUtils.toSnakeCase("Elven");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void oneWordLowerTest() {
        String expected = "elven";
        String actual = CaseUtils.toSnakeCase("elven");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void emptyTest() {
        String expected = "";
        String actual = CaseUtils.toSnakeCase("");
        Assert.assertEquals(expected, actual);
    }
}