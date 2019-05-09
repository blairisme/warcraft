/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.inject;

import com.evilbird.engine.common.lang.Persisted;

import javax.inject.Provider;

/**
 * Implementors of this interface provide instances of {@code T}, usually from
 * sources that require loading from storage.
 *
 * @param <T> the type of elements provided by the AssetProvider.
 *
 * @author Blair Butterworth
 */
public interface AssetProvider<T> extends Provider<T>, Persisted
{
}
