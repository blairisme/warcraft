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
import com.evilbird.engine.action.framework.CompositeAction;

/**
 * A concrete instance of the abstract {@link CompositeAction} class, useful
 * for testing purposes.
 *
 * @author Blair Butterworth
 */
public class TestCompositeAction extends CompositeAction
{
    public TestCompositeAction(Action... actions) {
        super(actions);
    }

    @Override
    public boolean act(float delta) {
        return false;
    }
}
