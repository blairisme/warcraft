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
import com.badlogic.gdx.ai.btree.leaf.Wait;
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Inject;

/**
 * A {@link LeafTask} implementation that waits for random length of time,
 * within the given minimum and maximum bounds, before completing successfully.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public class RandomWait<T> extends LeafTask<T>
{
    private Wait<T> waitTask;
    private float waitMinimum;
    private float waitMaximum;

    @Inject
    public RandomWait() {
        this.waitMinimum = 0f;
        this.waitMaximum = 1f;
    }

    public RandomWait(float waitMinimum, float waitMaximum) {
        this.waitMinimum = waitMinimum;
        this.waitMaximum = waitMaximum;
    }

    public RandomWait<T> waitMinimum(float waitMinimum) {
        this.waitMinimum = waitMinimum;
        return this;
    }

    public RandomWait<T> waitMaximum(float waitMaximum) {
        this.waitMaximum = waitMaximum;
        return this;
    }

    @Override
    public void start() {
        waitTask = new Wait<>(RandomUtils.nextFloat(waitMinimum, waitMaximum));
        waitTask.start();
    }

    @Override
    public Status execute() {
        return waitTask.execute();
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        RandomWait<T> randomWait = (RandomWait<T>)task;
        randomWait.waitMaximum = this.waitMaximum;
        randomWait.waitMinimum = this.waitMinimum;
        return randomWait;
    }
}
