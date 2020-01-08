/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
