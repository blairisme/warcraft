/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.inject;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Implementors of this interface provide instances of {@code T} identified by
 * a given {@link Identifier}.
 *
 * @param <T> the type of elements provided by the IdentifiedProvider.
 *
 * @author Blair Butterworth
 */
public interface IdentifiedProvider<T>
{
    T get(Identifier identifier);
}
