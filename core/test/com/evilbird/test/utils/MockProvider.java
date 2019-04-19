/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
