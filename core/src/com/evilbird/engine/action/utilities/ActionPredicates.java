/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.utilities;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.function.Predicate;

public class ActionPredicates
{
    public static Predicate<BasicAction> noError() {
        return new Predicate<BasicAction>() {
            @Override
            public boolean test(BasicAction value) {
                return !value.hasError();
            }
        };
    }
}
