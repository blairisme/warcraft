/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.google.gson.JsonParseException;

import java.lang.reflect.Constructor;

/**
 * @author Blair Butterworth
 */
public class ReflectionUtils
{
    public static Object getInstance(Class<?> type) {
        try {
            Constructor<?> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        }
        catch (ReflectiveOperationException error) {
            throw new SerializationException(error);
        }
    }
}
