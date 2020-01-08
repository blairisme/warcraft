/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.utils;

import org.mockito.Mockito;

import javax.inject.Provider;
import java.util.function.Supplier;

public class MockProvider<T> implements Provider<T>
{
    private Supplier<T> answer;

    public MockProvider(Class<T> type) {
        this.answer = () -> Mockito.mock(type);
    }

    public MockProvider(Supplier<T> answer) {
        this.answer = answer;
    }

    @Override
    public T get() {
        return answer.get();
    }
}
