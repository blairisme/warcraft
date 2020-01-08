/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.value;

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