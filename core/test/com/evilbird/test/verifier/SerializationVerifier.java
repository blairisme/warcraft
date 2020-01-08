/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.verifier;

import com.evilbird.engine.common.serialization.JsonSerializer;
import com.evilbird.engine.common.serialization.Serializer;
import com.evilbird.warcraft.type.WarcraftTypeRegistry;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SerializationVerifier<T>
{
    private Class<T> type;
    private String serialized;
    private T deserialized;

    public static <T> SerializationVerifier<T> forClass(Class<T> type) {
        return new SerializationVerifier<>(type);
    }

    private SerializationVerifier(Class<T> type) {
        this.type = type;
    }

    public SerializationVerifier withSerializedForm(String serialized) {
        this.serialized = serialized;
        return this;
    }

    public SerializationVerifier withSerializedResource(String resource) throws IOException {
        try (InputStream input = getClass().getResourceAsStream(resource);
             ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            IOUtils.copy(input, output);
            return withSerializedForm(output.toString(StandardCharsets.UTF_8.name()));
        }
    }

    public SerializationVerifier withDeserializedForm(T deserialized) {
        this.deserialized = deserialized;
        return this;
    }

    public void verify() {
        Validate.notNull(type);
        Validate.notNull(serialized);
        Validate.notNull(deserialized);

        Serializer serializer = new JsonSerializer(new WarcraftTypeRegistry());

        String json = serializer.serialize(deserialized, type);
        String jsonTrimmed = json.trim().replace("\r","");
        String serializedTrimmed = serialized.trim().replace("\r","");
        Assert.assertEquals(serializedTrimmed, jsonTrimmed);

        Object object = serializer.deserialize(json, type);
        Assert.assertEquals(deserialized, object);
    }


}
