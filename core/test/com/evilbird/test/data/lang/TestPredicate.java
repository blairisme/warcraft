/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.lang;

import com.evilbird.engine.action.Action;

import java.util.Objects;
import java.util.function.Predicate;

public class TestPredicate implements Predicate<Action>
{
    private boolean value;

    public TestPredicate(){
        this.value = true;
    }

    @Override
    public boolean test(Action action) {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return obj instanceof TestPredicate;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPredicate that = (TestPredicate) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}