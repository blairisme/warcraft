/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.common;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Decorator;
import com.badlogic.gdx.ai.btree.Task;

public abstract class SubTree<A, B> extends Decorator<A>
{
    private BehaviorTree<B> subtree;

    public SubTree(Task<B> rootTask) {
        this.subtree = new BehaviorTree<>(rootTask);
    }

    @Override
    public void run () {
        if (subtree.getStatus() == Status.FRESH) {
            A blackboard = getObject();
            B converted = convertObject(blackboard);
            subtree.setObject(converted);
        }
        if (subtree.getStatus() != Status.RUNNING) {
            subtree.start();
        }
        subtree.step();
    }

    protected abstract B convertObject(A object);
}
