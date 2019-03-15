/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

public class SerializedTypes
{
    private static Map<String, Class<?>> types;

    public static String getAlias(Class<?> type) {
        SerializedType serializedType = type.getAnnotation(SerializedType.class);
        if (serializedType == null) {
            throw new UnsupportedOperationException();
        }
        return serializedType.value();
    }

    public static Class<?> getType(String alias) {
        return getTypeMap().get(alias);
    }

    public static boolean hasAlias(Class<?> type) {
        return type.isAnnotationPresent(SerializedType.class);
    }

    public static boolean hasType(String alias) {
        return getTypeMap().containsKey(alias);
    }

    private static Map<String, Class<?>> getTypeMap() {
        if (types == null) {
            types = new HashMap<>();
            Reflections reflections = new Reflections("com.evilbird");
            for (Class<?> type : reflections.getTypesAnnotatedWith(SerializedType.class)) {
                String alias = getAlias(type);
                types.put(alias, type);
            }
        }
        return types;
    }
}
