/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
