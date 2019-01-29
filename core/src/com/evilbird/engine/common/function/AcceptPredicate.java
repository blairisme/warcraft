/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.function;

//TODO: Move into predicates
public class AcceptPredicate<T> implements Predicate<T>
{
    @Override
    public boolean test(T value)
    {
        return true;
    }
}
