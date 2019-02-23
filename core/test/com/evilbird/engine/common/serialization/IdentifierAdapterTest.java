/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.evilbird.engine.common.lang.GenericIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.IdentifierAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate logic in the {@link com.evilbird.engine.common.lang.IdentifierAdapter} class.
 *
 * @author Blair Butterworth
 */
public class IdentifierAdapterTest
{
    private Gson serializer;

    public IdentifierAdapterTest() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Identifier.class, new IdentifierAdapter());
        serializer = gsonBuilder.create();
    }

    @Test
    public void serializeTest() {
        Identifier identifier = GenericIdentifier.Unknown;

        String expected = "{\"type\":\"com.evilbird.engine.common.lang.GenericIdentifier\",\"value\":\"Unknown\"}";
        String actual = serializer.toJson(identifier, Identifier.class);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deserializeTest() {
        String json = "{\"type\":\"com.evilbird.engine.common.lang.GenericIdentifier\",\"value\":\"Unknown\"}";

        Identifier expected = GenericIdentifier.Unknown;
        Identifier actual = serializer.fromJson(json, Identifier.class);

        Assert.assertEquals(expected, actual);
    }


}
