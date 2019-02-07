/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.function;

import com.evilbird.engine.common.lang.Resettable;

/**
 * Implementors of this interface provide an object of a given type and whose
 * underlying mechanism can be reset, if stateful.
 *
 * @param <T> the type of object supplied by the predicate.
 * @author Blair Butterworth
 */
public interface ResettablePredicate<T> extends Predicate<T>, Resettable
{
}
