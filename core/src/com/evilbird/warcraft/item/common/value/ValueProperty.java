/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.value;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Provides read and write access to a value.
 *
 * @author Blair Butterworth
 */
public class ValueProperty
{
    private Supplier<Value> getter;
    private Consumer<Value> setter;

    public ValueProperty(Supplier<Value> getter, Consumer<Value> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public Value getValue() {
        return getter.get();
    }

    public void setValue(Value value) {
        setter.accept(value);
    }
}