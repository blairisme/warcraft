/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour.framework.task;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;

/**
 * An {@link ActionTask} specialization that assigns an action to a given
 * subject but doesn't monitor its progress, succeeding immediately.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public abstract class AsyncActionTask<T> extends ActionTask<T>
{
    public AsyncActionTask(ActionFactory factory) {
        super(factory);
    }

    @Override
    protected Status getStatus(Action action) {
        return Status.SUCCEEDED;
    }
}
