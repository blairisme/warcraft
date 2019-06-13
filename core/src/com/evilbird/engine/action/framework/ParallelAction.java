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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Instances of this {@link Action} execute a set of delegate actions all at
 * the same time.
 *
 * @author Blair Butterworth
 */
public class ParallelAction extends CompositeAction
{
    private List<Action> completed;

    @SerializedConstructor
    private ParallelAction() {
    }

    public ParallelAction(Action ... actions) {
        super(actions);
        this.completed = new ArrayList<>(actions.length);
        resetCompletion();
    }

    public ParallelAction(List<Action> actions) {
        super(actions);
        this.completed = new ArrayList<>(actions.size());
        resetCompletion();
    }

    @Override
    public void add(Action action) {
        super.add(action);
        resetCompletion();
    }

    @Override
    public boolean act(float delta) {
        ListIterator<Action> iterator = actions.listIterator();
        while (iterator.hasNext()) {
            Action action = iterator.next();
            if (action.act(delta)) {
                iterator.remove();
                completed.add(action);
            }
        }
        return actions.isEmpty();
    }

    public Collection<Action> getActions() {
        Collection<Action> result = new ArrayList<>(actions);
        result.addAll(completed);
        return result;
    }

    @Override
    public boolean isEmpty() {
        return actions.isEmpty() && completed.isEmpty();
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
        if (! completed.isEmpty()) {
            actions.addAll(completed);
        }
        completed.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        ParallelAction that = (ParallelAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(completed, that.completed)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(completed)
            .toHashCode();
    }
}