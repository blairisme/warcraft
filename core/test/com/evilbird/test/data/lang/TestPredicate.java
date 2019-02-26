/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.lang;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.function.Predicate;

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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TestPredicate;
    }
}