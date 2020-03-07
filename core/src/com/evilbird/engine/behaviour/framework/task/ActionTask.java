/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour.framework.task;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;

/**
 * A {@link LeafTask} that wraps an {@link Action}.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public abstract class ActionTask<T> extends LeafTask<T>
{
    private Action action;
    private ActionFactory factory;

    public ActionTask(ActionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void start() {
        action = getAction(factory);
    }

    @Override
    public Status execute() {
        return getStatus(action);
    }

    @Override
    public void end() {
        action = null;
    }

    protected abstract Action getAction(ActionFactory factory);

    protected Status getStatus(Action action) {
        return ActionTaskUtils.getStatus(action);
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        ActionTask<T> actionTask = (ActionTask<T>)task;
        actionTask.action = this.action;
        actionTask.factory = this.factory;
        return actionTask;
    }
}
