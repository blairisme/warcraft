/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.common.task;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.object.GameObject;

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

    protected GameObject getRecipient(Action action) {
        return action.getSubject();
    }

    protected Status getStatus(Action action) {
        if (action == null) {
            return Status.FAILED;
        }
        if (getRecipient(action).hasAction(action)) {
            return Status.RUNNING;
        }
        if (action.hasError()) {
            return Status.FAILED;
        }
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        ActionTask<T> actionTask = (ActionTask<T>)task;
        actionTask.action = this.action;
        actionTask.factory = this.factory;
        return actionTask;
    }
}
