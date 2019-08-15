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
import java.util.Objects;

/**
 * Contains common functions for working with reflection.
 *
 * @author Blair Butterworth
 */
public class ReflectionUtils
{
    /**
     * ReflectionUtils instances should NOT be constructed in standard
     * programming. Instead, the class should be used as
     * {@code ReflectionUtils.getInstance(cls)}.
     */
    private ReflectionUtils() {
    }

    /**
     * Returns a new instance of the specified class using a no argument
     * constructor. The constructor does not need to be accessible to be
     * invoked, I.E., it can be private.
     *
     * @param <T> the type to be constructed.
     * @param type the class to be constructed, not {@code null}.
     *
     * @return new instance of {@code cls}, not {@code null}.
     *
     * @throws NullPointerException if {@code type} is {@code null}.
     * @throws ReflectionException  if a matching constructor cannot be found
     *                              or if the invocation is not permitted by
     *                              the current security policy.
     */
    public static <T> T invokeConstructor(Class<T> type) {
        try {
            Objects.requireNonNull(type);
            Constructor<T> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        }
        catch (ReflectiveOperationException error) {
            throw new ReflectionException(error);
        }
    }

    /**
     * Invokes any methods belonging to the given object that have been marked
     * with the given annotation. The annotated methods do not need to be
     * accessible to be invoked.  I.E., they can be private.
     *
     * @param object        invoke method on this object.
     * @param annotation    invoke methods marked with this annotation.
     *
     * @throws NullPointerException if {@code type} or {@code annotation} is
     *                              {@code null}.
     * @throws ReflectionException  if the invocation is not permitted by
     *                              the current security policy.
     */
    public static void invokeMethod(Object object, Class<? extends Annotation> annotation) {
        try {
            Objects.requireNonNull(object);
            Objects.requireNonNull(annotation);
            for (Method method: MethodUtils.getMethodsWithAnnotation(object.getClass(), annotation, true, true)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
        catch (ReflectiveOperationException error) {
            throw new ReflectionException(error);
        }
    }
}
