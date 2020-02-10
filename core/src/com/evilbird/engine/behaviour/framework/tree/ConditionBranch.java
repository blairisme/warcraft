/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour.framework.tree;

import com.badlogic.gdx.ai.btree.Decorator;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.function.Functions;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A branch task that conditionally executes a given task based on the tasks
 * blackboard data.
 *
 * @param <T> type of the blackboard object used by the task.
 * @param <D> type of object used by the guards condition.
 *
 * @author Blair Butterworth
 */
public class ConditionBranch<T, D> extends Decorator<T>
{
    private transient Function<T, D> target;
    private transient Map<Predicate<D>, Task<T>> tasks;

    @Inject
    public ConditionBranch() {
        tasks = new LinkedHashMap<>(3);
        target = Functions.supply(null);
    }

    @Override
    public void start() {
        T data = getObject();
        D value = target.apply(data);

        for (Entry<Predicate<D>, Task<T>> task: tasks.entrySet()) {
            Predicate<D> condition = task.getKey();
            if (condition.test(value)) {
                child = task.getValue();
                break;
            }
        }
        super.start();
    }

    public ConditionBranch<T,D> from(Function<T, D> target) {
        this.target = target;
        return this;
    }

    public ConditionBranch<T, D> branch(Predicate<D> condition, Task<T> task) {
        this.tasks.put(condition, task);
        return this;
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        ConditionBranch<T, D> conditionBranch = (ConditionBranch<T, D>)task;
        conditionBranch.tasks = this.tasks;
        conditionBranch.target = this.target;
        return conditionBranch;
    }
}
