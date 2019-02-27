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
    private transient boolean restarted;
    private transient boolean reset;
    private transient boolean complete;

    public TestBasicAction() {
        resetState();
        complete = true;
    }

    @Override
    public boolean act(float delta) {
        invoked = true;
        return complete;
    }

    @Override
    public void restart() {
        restarted = true;
    }

    @Override
    public void reset() {
        reset = true;
    }

    public boolean getInvoked() {
        return invoked;
    }

    public boolean getRestarted() {
        return restarted;
    }

    public boolean getReset() {
        return reset;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void resetState() {
        invoked = false;
        restarted = false;
        reset = false;
    }
}