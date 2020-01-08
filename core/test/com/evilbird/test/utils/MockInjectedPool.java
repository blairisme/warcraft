/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.utils;

import com.evilbird.engine.common.inject.InjectedPool;
import org.mockito.Mockito;

/**
 * An {@link InjectedPool} specialization that returns mocked objects provided
 * by Mockito.
 *
 * @author Blair Butterworth
 */
public class MockInjectedPool<T> extends InjectedPool<T>
{
    private Class<T> type;

    public MockInjectedPool(Class<T> type) {
        super(null);
        this.type = type;
    }

    @Override
    protected T newObject() {
        return Mockito.mock(type);
    }
}
