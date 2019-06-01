/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import java.io.Reader;
import java.io.Writer;

/**
 * Implementors of this interface serialize objects into their equivalent
 * textual representation. Methods are provided to convert objects into text
 * and text into objects.
 *
 * @author Blair Butterworth
 */
public interface Serializer
{
    <T> String serialize(T value, Class<T> type) throws SerializationException;

    <T> void serialize(T value, Class<T> type, Writer writer) throws SerializationException;

    <T> T deserialize(String value, Class<T> type) throws SerializationException;

    <T> T deserialize(Reader reader, Class<T> type) throws SerializationException;
}
