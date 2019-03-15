/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import java.lang.reflect.Constructor;

public class ReflectionUtils
{
    private ReflectionUtils() {
    }

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
