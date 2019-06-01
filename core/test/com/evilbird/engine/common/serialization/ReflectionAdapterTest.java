/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
