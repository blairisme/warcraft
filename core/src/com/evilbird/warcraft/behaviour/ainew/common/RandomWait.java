/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.common;

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
    }

    public RandomWait(float waitMinimum, float waitMaximum) {
        this.waitMinimum = waitMinimum;
        this.waitMaximum = waitMaximum;
    }

    public void setWaitMinimum(float waitMinimum) {
        this.waitMinimum = waitMinimum;
    }

    public void setWaitMaximum(float waitMaximum) {
        this.waitMaximum = waitMaximum;
    }

    @Override
    public void start() {
        waitTask = new Wait<>(RandomUtils.nextFloat(waitMinimum, waitMaximum));
        waitTask.start();
    }

    @Override
    public Status execute() {
        Status status = waitTask.execute();

        if (status == Status.SUCCEEDED) {
            return status;
        }
        return status;
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        return null;
    }
}
