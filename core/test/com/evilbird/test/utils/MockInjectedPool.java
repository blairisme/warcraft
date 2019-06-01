/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
