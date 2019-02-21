/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.evilbird.engine.common.lang.Identifier;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;

import java.io.IOException;

/**
 * Instances of this class serialize and deserialize {@link Identifier
 * Identifiers}.
 *
 * @author Blair Butterworth
 */
public class IdentifierAdapter extends TypeAdapter<Identifier>
{
    private static final String TYPE = "type";
    private static final String VALUE = "value";

    @Override
    public Identifier read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            return null;
        } else {
            return readIdentifier(reader);
        }
    }

    private Identifier readIdentifier(JsonReader reader) throws IOException {
        try {
            reader.beginObject();
            Class<?> type = readType(reader);
            String value = readValue(reader);
            reader.endObject();
            return newIdentifier(type, value);
        }
        catch (ReflectiveOperationException reflectionError) {
            throw new JsonSyntaxException(reflectionError);
        }
    }

    private Class<?> readType(JsonReader reader) throws IOException, ReflectiveOperationException {
        reader.nextName();
        return Class.forName(reader.nextString());
    }

    private String readValue(JsonReader reader) throws IOException {
        reader.nextName();
        return reader.nextString();
    }

    @SuppressWarnings("unchecked")
    private Identifier newIdentifier(Class<?> type, String value) throws ReflectiveOperationException {
        if (type.isEnum()) {
            return (Identifier)EnumUtils.getEnum((Class<Enum>)type, value);
        }
        return (Identifier)ConstructorUtils.invokeConstructor(type, value);
    }

    @Override
    public void write(JsonWriter writer, Identifier value) throws IOException {
        if (value == null) {
            writer.nullValue();
        } else {
            writeIdentifier(writer, value);
        }
    }

    private void writeIdentifier(JsonWriter writer, Identifier value) throws IOException {
        writer.beginObject();
        writer.name(TYPE);
        writer.value(value.getClass().getName());
        writer.name(VALUE);
        writer.value(value.toString());
        writer.endObject();
    }
}
