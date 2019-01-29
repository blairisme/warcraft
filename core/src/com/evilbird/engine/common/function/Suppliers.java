/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.function;

/**
 * Instances of this class define commonly used {@link Supplier Suppliers}.
 *
 * @author Blair Butterworth
 */
public class Suppliers
{
    private Suppliers() {
        throw new UnsupportedOperationException();
    }

    public static Supplier<Boolean> isTrue() {
        return new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                return Boolean.TRUE;
            }
        };
    }

    public static <T> Supplier<T> constantValue(T value){
        return new Supplier<T>() {
            @Override
            public T get() {
                return value;
            }
        };
    }

    public static ResettableSupplier<Boolean> counter(int times) {
        return new Counter(times);
    }

    private static class Counter implements ResettableSupplier<Boolean> {
        private int times;
        private int count;

        public Counter(int times) {
            this.times = times;
            this.count = 0;
        }

        @Override
        public Boolean get() {
            return count++ < times;
        }

        @Override
        public void reset() {
            count = 0;
        }
    }
}
