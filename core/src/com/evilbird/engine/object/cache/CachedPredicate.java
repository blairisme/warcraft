/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.cache;

import java.util.function.Predicate;

import static com.evilbird.engine.object.cache.CacheRetentionType.Modify;

@CacheRetention(Modify)
public class CachedPredicate<T> implements Predicate<T>
{
    private Predicate<T> delegate;

    public CachedPredicate(Predicate<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean test(T object) {
        return delegate.test(object);
    }
}
