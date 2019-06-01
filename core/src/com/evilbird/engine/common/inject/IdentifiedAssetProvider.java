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
import com.evilbird.engine.common.lang.Persisted;

/**
 * Implementors of this interface provide instances of {@code T} identified by
 * a given {@link Identifier}. New object instances are usually created from
 * sources that require loading from storage.
 *
 * @param <T> the type of elements provided by the IdentifiedAssetProvider.
 *
 * @author Blair Butterworth
 */
public interface IdentifiedAssetProvider<T> extends IdentifiedProvider<T>, Persisted
{
}
