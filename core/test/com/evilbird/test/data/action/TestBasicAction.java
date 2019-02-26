/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.action;

import com.evilbird.engine.action.framework.BasicAction;

/**
 * A concrete instance of the abstract {@link BasicAction} class, useful
 * for testing purposes.
 *
 * @author Blair Butterworth
 */
public class TestBasicAction extends BasicAction
{
    private transient boolean invoked;

    public TestBasicAction() {
        invoked = false;
    }

    @Override
    public boolean act(float delta) {
        invoked = true;
        return true;
    }

    public boolean getInvoked() {
        return invoked;
    }

    public void reset() {
        invoked = false;
    }
}