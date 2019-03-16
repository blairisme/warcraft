/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.action;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;

public class TestActions
{
    private TestActions() {
    }

    public static Action newAction(String id) {
        TestBasicAction result = new TestBasicAction();
        result.setIdentifier(new TextIdentifier(id));
        return result;
    }
}
