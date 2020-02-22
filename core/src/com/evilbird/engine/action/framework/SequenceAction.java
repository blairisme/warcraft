/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Instances of this {@link Action} execute a set of delegate actions in
 * sequence, one after another until complete.
 *
 * @author Blair Butterworth
 */
public class SequenceAction extends CompositeAction
{
    private int index;
    private transient Action current;

    @SerializedConstructor
    private SequenceAction() {
    }

    public SequenceAction(Action... sequence) {
        super(sequence);
        resetIndex();
    }

    public SequenceAction(List<Action> sequence) {
        super(sequence);
        resetIndex();
    }

    @Override
    public Action add(Action action) {
        Action result = super.add(action);
        resetIndex();
        return result;
    }

    @Override
    public boolean act(float delta) {
        boolean result = true;
        if (current != null) {
            result = current.run(delta);
            if (result && ++index < actions.size()) {
                current = actions.get(index);
                result = false;
            }
        }
        return result;
    }

    @Override
    public void restart() {
        super.restart();
        resetIndex();
    }

    @Override
    public void reset() {
        super.reset();
        resetIndex();
    }

    private void resetIndex() {
        index = 0;
        if (! actions.isEmpty()) {
            current = actions.get(0);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        SequenceAction action = (SequenceAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(index, action.index)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(index)
            .toHashCode();
    }
}
