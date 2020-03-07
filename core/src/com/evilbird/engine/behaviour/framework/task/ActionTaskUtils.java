/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour.framework.task;

import com.badlogic.gdx.ai.btree.Task.Status;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.AbstractAction;

import static com.badlogic.gdx.ai.btree.Task.Status.CANCELLED;
import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.FRESH;
import static com.badlogic.gdx.ai.btree.Task.Status.RUNNING;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;

/**
 * Provides common utilities for action tasks.
 *
 * @author Blair Butterworth
 */
public class ActionTaskUtils
{
    public static Status getStatus(Action action) {
        if (action instanceof AbstractAction) {
            return getBasicActionStatus((AbstractAction)action);
        }
        return getGenericActionStatus(action);
    }

    private static Status getBasicActionStatus(AbstractAction action) {
        switch(action.getStatus()) {
            case Fresh: return FRESH;
            case Running: return RUNNING;
            case Succeeded: return SUCCEEDED;
            case Failed: return FAILED;
            case Cancelled: return CANCELLED;
            default: throw new UnsupportedOperationException();
        }
    }

    private static Status getGenericActionStatus(Action action) {
        if (action == null) {
            return FAILED;
        }
        if (action.getSubject().hasAction(action)) {
            return RUNNING;
        }
        if (action.isFailed()) {
            return FAILED;
        }
        return Status.SUCCEEDED;
    }

    private ActionTaskUtils() {
    }
}
