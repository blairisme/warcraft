/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour.framework.branch;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;

/**
 * Represents a behaviour tree embedded within a larger behaviour tree.
 * Subtrees support an independent blackboard data type.
 *
 * @param <A>   the type of blackboard data used by the parent of the subtree.
 * @param <B>   the type of blackboard data used by the subtree.
 *
 * @author Blair Butterworth
 */
public abstract class SubTree<A, B> extends Task<A> 
{
    private Task<B> rootTask;
    private BehaviorTree<B> subtree;

    public SubTree(Task<B> rootTask) {
        this.rootTask = rootTask;
        this.subtree = new BehaviorTree<>(rootTask);
    }

    @Override
    public void start() {
        A blackboard = getObject();
        B converted = convertObject(blackboard);
        subtree.setObject(converted);
    }

    @Override
    public void run () {
        subtree.step();
        switch (rootTask.getStatus()) {
            case SUCCEEDED: {
                success();
                return;
            }
            case FAILED: {
                fail();
                return;
            }
            case RUNNING: {
                running();
                return;
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    protected int addChildToTask(Task<A> child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getChildCount () {
        return 0;
    }

    @Override
    public Task<A> getChild(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void childRunning(Task<A> runningTask, Task<A> reporter) {
    }

    @Override
    public final void childFail(Task<A> runningTask) {
    }

    @Override
    public final void childSuccess(Task<A> runningTask) {
    }

    @Override
    protected Task<A> copyTo(Task<A> task) {
        SubTree<A, B> result = (SubTree<A, B>)task;
        result.rootTask = rootTask;
        result.subtree = subtree;
        return result;
    }

    protected abstract B convertObject(A object);
}
