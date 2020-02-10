/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour.framework.guard;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.leaf.Wait;
import com.evilbird.engine.common.function.Functions;
import com.evilbird.engine.common.function.Predicates;
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Inject;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.RUNNING;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;

/**
 * A guard task that waits for the data in the tasks blackboard to match a
 * given condition before returning successfully.
 *
 * @param <T> type of the blackboard object used by the task.
 * @param <D> type of object used by the guards condition.
 *
 * @author Blair Butterworth
 */
public class ConditionWait<T, D> extends LeafTask<T>
{
    private Wait<T> wait;
    private float waitMaximum;
    private Function<T, D> target;
    private Predicate<D> condition;

    @Inject
    public ConditionWait() {
        this.waitMaximum = 1f;
        this.target = Functions.supply(null);
        this.condition = Predicates.accept();
    }

    public ConditionWait<T,D> from(Function<T, D> target) {
        this.target = target;
        return this;
    }

    public ConditionWait<T, D> when(Predicate<D> condition) {
        this.condition = condition;
        return this;
    }

    public ConditionWait<T, D> maximum(float waitMaximum) {
        this.waitMaximum = waitMaximum;
        return this;
    }

    @Override
    public void start() {
        wait = new Wait<>(RandomUtils.nextFloat(0, waitMaximum));
        wait.start();
    }

    @Override
    public Status execute() {
        T data = getObject();
        D value = target.apply(data);

        if (condition.test(value)) {
            return SUCCEEDED;
        }
        if (wait.execute() == SUCCEEDED) {
            return FAILED;
        }
        return RUNNING;
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        ConditionWait<T, D> conditionWait = (ConditionWait<T, D>)task;
        conditionWait.wait = this.wait;
        conditionWait.waitMaximum = this.waitMaximum;
        conditionWait.condition = this.condition;
        conditionWait.target = this.target;
        return conditionWait;
    }
}
