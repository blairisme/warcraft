/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

/**
 * Implementors of this interface provide methods for accessing an
 * {@link Identifier} that uniquely identifies the given objects among similar
 * objects.
 *
 * @author Blair Butterworth
 */
public interface Identifiable <T extends Identifier>
{
    T getIdentifier();
}
