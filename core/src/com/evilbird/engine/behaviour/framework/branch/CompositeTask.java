/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour.framework.branch;

import com.badlogic.gdx.ai.btree.BranchTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * A {@link BranchTask} specialization that executes all of its children until
 * they complete. If any child task fails then the composite will fail but only
 * after the execution of the remaining children completes.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public class CompositeTask<T> extends BranchTask<T>
{
    private boolean running;
    private boolean succeeded;
    private Iterator<Task<T>> iterator;

    public CompositeTask() {
        this(new Array<>());
    }

    public CompositeTask(Array<Task<T>> tasks) {
        super(tasks);
        running = false;
        succeeded = true;
    }

    @Override
    public void run () {
        iterator = children.iterator();

        while (iterator.hasNext()) {
            run(iterator.next());
        }
        if (running) {
            running();
        } else if (succeeded) {
            success();
        } else {
            fail();
        }
    }

    protected void run(Task<T> task) {
        if (task.getStatus() == Task.Status.RUNNING) {
            task.run();
        } else {
            task.setControl(this);
            task.start();
            if (task.checkGuard(this)) {
                task.run();
            } else {
                task.fail();
            }
        }
    }

    @Override
    public void childRunning(Task<T> task, Task<T> reporter) {
        running = true;
    }

    @Override
    public void childSuccess(Task<T> runningTask) {
        iterator.remove();
    }

    @Override
    public void childFail(Task<T> runningTask) {
        iterator.remove();
        succeeded = false;
    }

    @Override
    public void reset() {
        super.reset();
        running = false;
        succeeded = true;
    }

    public void removeChild(Task<T> child) {
        children.removeValue(child, true);
    }
}
