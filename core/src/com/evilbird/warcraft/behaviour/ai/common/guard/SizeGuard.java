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
import java.util.Collection;
import java.util.function.Function;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.engine.common.function.Functions.supply;
import static java.util.Collections.emptyList;

/**
 * A guard task that ensures that blackboard data on which it operates contains
 * a given number of elements.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public class SizeGuard<T> extends LeafTask<T>
{
    protected transient Function<T, Collection<?>> targetSupplier;
    protected transient int minimum;
    protected transient int maximum;

    @Inject
    public SizeGuard() {
        this.targetSupplier = supply(emptyList());
        this.minimum = 0;
        this.maximum = Integer.MAX_VALUE;
    }

    @Override
    public Status execute() {
        Collection<?> elements = targetSupplier.apply(getObject());
        if (elements != null) {
            int size = elements.size();
            if (size >= minimum && size <= maximum) {
                return SUCCEEDED;
            }
        }
        return FAILED;
    }

    public SizeGuard<T> minimum(int minimum) {
        this.minimum = minimum;
        return this;
    }

    public SizeGuard<T> setMaximum(int maximum) {
        this.maximum = maximum;
        return this;
    }

    public SizeGuard<T> from(Function<T, Collection<?>> targetSupplier) {
        this.targetSupplier = targetSupplier;
        return this;
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        SizeGuard<T> sizeGuard = (SizeGuard<T>)task;
        sizeGuard.targetSupplier = this.targetSupplier;
        return sizeGuard;
    }
}
