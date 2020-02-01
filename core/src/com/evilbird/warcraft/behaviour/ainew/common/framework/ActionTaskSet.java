/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.common.framework;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.object.GameObject;

import java.util.Collection;

/**
 * A {@link LeafTask} that wraps an {@link Action}.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public abstract class ActionTaskSet<T> extends LeafTask<T>
{
    private ActionFactory factory;
    private Collection<Action> actions;

    public ActionTaskSet(ActionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void start() {
        actions = getActions(factory);
    }

    @Override
    public Status execute() {
        CollectionUtils.removeIf(actions, action -> getStatus(action) != Status.RUNNING);
        return actions.isEmpty() ? Status.SUCCEEDED : Status.RUNNING;
    }

    protected abstract Collection<Action> getActions(ActionFactory factory);

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
        ActionTaskSet<T> actionTask = (ActionTaskSet<T>)task;
        actionTask.actions = this.actions;
        actionTask.factory = this.factory;
        return actionTask;
    }
}
