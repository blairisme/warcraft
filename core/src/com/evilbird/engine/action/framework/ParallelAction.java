/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Instances of this {@link Action} execute a set of delegate actions all at
 * the same time.
 *
 * @author Blair Butterworth
 */
public class ParallelAction extends CompositeAction
{
    private List<Action> remaining;

    @SerializedConstructor
    private ParallelAction() {
    }

    public ParallelAction(Action... actions) {
        super(actions);
        this.remaining = new ArrayList<>(actions.length);
        resetCompletion();
    }

    public ParallelAction(List<Action> actions) {
        super(actions);
        this.remaining = new ArrayList<>(actions.size());
        resetCompletion();
    }

    @Override
    public Action add(Action action) {
        Action result = super.add(action);
        resetCompletion();
        return result;
    }

    @Override
    public boolean act(float delta) {
        CollectionUtils.removeIf(remaining, action -> action.run(delta));
        return remaining.isEmpty();
    }

    @Override
    public void restart() {
        resetCompletion();
        super.restart();
    }

    @Override
    public void reset() {
        resetCompletion();
        super.reset();
    }

    private void resetCompletion() {
        remaining.clear();
        remaining.addAll(actions);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        ParallelAction that = (ParallelAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(remaining, that.remaining)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(remaining)
            .toHashCode();
    }
}