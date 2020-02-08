/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.common.guard;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

import javax.inject.Inject;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;

/**
 * A guard task that ensures that blackboard data on which it operates
 * satisfies a given {@link Predicate condition}.
 *
 * @param <T> type of the blackboard object used by the task.
 * @param <D> type of object used by the guards condition.
 *
 * @author Blair Butterworth
 */
public class ConditionGuard<T, D> extends LeafTask<T>
{
    protected transient Function<T, D> target;
    protected transient Predicate<D> condition;

    @Inject
    public ConditionGuard() {
    }

    @Override
    public Status execute() {
        T data = getObject();
        D value = target.apply(data);
        return condition.test(value) ? SUCCEEDED : FAILED;
    }

    public ConditionGuard<T,D> from(Function<T, D> target) {
        this.target = target;
        return this;
    }

    public ConditionGuard<T, D> pass(Predicate<D> condition) {
        this.condition = condition;
        return this;
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        ConditionGuard<T, D> conditionGuard = (ConditionGuard<T, D>)task;
        conditionGuard.target = this.target;
        conditionGuard.condition = this.condition;
        return conditionGuard;
    }
}
