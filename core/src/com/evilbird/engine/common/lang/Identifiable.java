/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.lang;

/**
 * Implementors of this interface provide methods for accessing an
 * {@link Identifier} that uniquely identifies the given objects among similar
 * objects.
 *
 * @param <T> the type of identifiers provided by the Identifiable.
 *
 * @author Blair Butterworth
 */
public interface Identifiable<T extends Identifier>
{
    T getIdentifier();
}
