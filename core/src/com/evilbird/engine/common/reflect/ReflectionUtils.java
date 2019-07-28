/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.reflect;

import com.evilbird.engine.common.error.ReflectionException;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Contains common functions for working with reflection.
 *
 * @author Blair Butterworth
 */
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
            throw new ReflectionException(error);
        }
    }

    public static void invokeMethod(Object object, Class<? extends Annotation> annotation) {
        for (Method method: MethodUtils.getMethodsWithAnnotation(object.getClass(), annotation, true, true)) {
            invokeMethod(method, object);
        }
    }

    public static void invokeMethod(Method method, Object object) {
        try {
            method.setAccessible(true);
            method.invoke(object);
        }
        catch (ReflectiveOperationException error) {
            throw new ReflectionException(error);
        }
    }
}
