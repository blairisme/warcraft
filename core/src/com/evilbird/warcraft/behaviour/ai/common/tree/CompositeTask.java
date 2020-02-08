/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.common.tree;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Parallel;

/**
 * A {@link Parallel} task specialization that supports child removal.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public class CompositeTask<T> extends Parallel<T>
{
    public void removeChild(Task<T> child) {
        children.removeValue(child, true);
    }
}
