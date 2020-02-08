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
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Inject;

/**
 * A {@link LeafTask} implementation that randomly returns a
 * {@link Status#FAILED failed status}.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public class RandomExecutor<T> extends LeafTask<T>
{
    @Inject
    public RandomExecutor() {
    }

    @Override
    public Status execute() {
        return RandomUtils.nextBoolean() ? Status.FAILED : Status.SUCCEEDED;
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        return task;
    }

}
