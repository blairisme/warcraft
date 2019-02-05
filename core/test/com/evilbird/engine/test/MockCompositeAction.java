/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.test;

import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.CompositeAction;

public class MockCompositeAction extends CompositeAction
{
    public MockCompositeAction(Action... actions) {
        super(actions);
    }

    @Override
    public boolean act(float delta) {
        return false;
    }
}
