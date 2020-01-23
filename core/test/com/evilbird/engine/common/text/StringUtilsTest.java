/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.text;

import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link StringUtils} class.
 *
 * @author Blair Butterworth
 */
public class StringUtilsTest
{
    @Test
    public void toSnakeCaseTest() {
        String expected = "elven_archer";
        String actual = StringUtils.toSnakeCase("ElvenArcher");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void oneWordUpperTest() {
        String expected = "elven";
        String actual = StringUtils.toSnakeCase("Elven");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void oneWordLowerTest() {
        String expected = "elven";
        String actual = StringUtils.toSnakeCase("elven");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void emptyTest() {
        String expected = "";
        String actual = StringUtils.toSnakeCase("");
        Assert.assertEquals(expected, actual);
    }
}