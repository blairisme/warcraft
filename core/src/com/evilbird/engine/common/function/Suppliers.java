/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.function;

import com.badlogic.gdx.math.MathUtils;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static java.util.Collections.singleton;

/**
 * Instances of this class define commonly used {@link Supplier Suppliers}.
 *
 * @author Blair Butterworth
 */
public class Suppliers
{
    private Suppliers() {
        throw new UnsupportedOperationException();
    }

    public static <T> Supplier<Collection<T>> constant(Collection<T> value) {
        return () -> value;
    }

    public static <T> Supplier<Collection<T>> constant(T value) {
        return () -> singleton(value);
    }

    public static BiFunction<Float, Float, Float> increment() {
        return (current, delta) -> MathUtils.clamp(current + delta, 0f, Float.MAX_VALUE);
    }

    public static BiFunction<Float, Float, Float> decrement() {
        return (current, delta) -> MathUtils.clamp(current - delta, 0f, Float.MAX_VALUE);
    }
}
