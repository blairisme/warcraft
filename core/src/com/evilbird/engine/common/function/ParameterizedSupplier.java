/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.function;

import java.util.function.Function;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * @param <K> the type of the input to the function
 * @param <V> the type of the result of the function
 *
 * @author Blair Butterworth
 */
public interface ParameterizedSupplier<K, V> extends Function<K, V>
{
    V get(K key);

    @Override
    default V apply(K k) {
        return get(k);
    }
}
