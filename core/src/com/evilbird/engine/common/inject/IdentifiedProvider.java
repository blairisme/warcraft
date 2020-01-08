/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
