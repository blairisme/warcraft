/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.action;

import com.evilbird.engine.action.ActionResult;
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
    public ActionResult act(float delta) {
        invoked = true;
        return ActionResult.Complete;
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