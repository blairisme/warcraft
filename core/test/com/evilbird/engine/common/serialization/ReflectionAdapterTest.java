/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.serialization;

import com.evilbird.warcraft.type.WarcraftTypeRegistry;
import org.junit.Before;

/**
 * Instances of this unit test validate logic in the {@link ReflectionAdapter}
 * class.
 *
 * @author Blair Butterworth
 */
public class ReflectionAdapterTest
{
    private Serializer serializer;

    @Before
    public void setup() {
        serializer = new JsonSerializer(new WarcraftTypeRegistry());
    }




//    @Test
//    public void serializeTest() {
//        Identifier identifier = GenericIdentifier.Unknown;
//
//        String expected = "{\"type\":\"com.evilbird.engine.common.lang.GenericIdentifier\",\"value\":\"Unknown\"}";
//        String actual = serializer.toJson(identifier, Identifier.class);
//
//        Assert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void deserializeTest() {
//        String json = "{\"type\":\"com.evilbird.engine.common.lang.GenericIdentifier\",\"value\":\"Unknown\"}";
//
//        Identifier expected = GenericIdentifier.Unknown;
//        Identifier actual = serializer.fromJson(json, Identifier.class);
//
//        Assert.assertEquals(expected, actual);
//    }
}
