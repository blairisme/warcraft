/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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

    public SequenceAction(Action ... sequence) {
        super(sequence);
        resetIndex();
    }

    public SequenceAction(List<Action> sequence) {
        super(sequence);
        resetIndex();
    }

    @Override
    public boolean act(float delta) {
        boolean result = true;
        if (current != null) {
            result = current.act(delta);
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
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

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
