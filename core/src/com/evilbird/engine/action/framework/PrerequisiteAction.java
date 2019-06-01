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

import java.util.function.Predicate;

/**
 * Instances of this {@link Action Action} execute an <code>Action</code>
 * if a given {@link Predicate} evaluates to <code>true</code>. If it evaluates
 * to false a prerequisite <code>Action</code> is executed first.
 *
 * @author Blair Butterworth
 */
public class PrerequisiteAction extends CompositeAction
{
    private transient Action action;
    private transient Action requisite;
    private transient Action current;
    private Predicate<Action> predicate;

    @SerializedConstructor
    private PrerequisiteAction() {
    }

    public PrerequisiteAction(Action action, Action requisite, Predicate<Action> predicate) {
        super(action, requisite);
        this.predicate = predicate;
    }

    @Override
    public boolean act(float delta) {
        if (action == null) {
            action = getPrimary();
        }
        if (requisite == null) {
            requisite = getRequisite();
        }
        if (current == null) {
            current = getInitialAction();
        }
        if (current == requisite) {
            return requisiteAct(delta);
        }
        return actionAct(delta);
    }

    private Action getInitialAction() {
        return predicate.test(action) ? action : requisite;
    }

    private boolean requisiteAct(float delta) {
        if (requisite.act(delta)) {
            requisite.restart();
            current = action;
        }
        return requisite.hasError();
    }

    private boolean actionAct(float delta) {
        if (!predicate.test(action)) {
            action.restart();
            current = requisite;
            return false;
        }
        return action.act(delta);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        PrerequisiteAction that = (PrerequisiteAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(predicate, that.predicate)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(predicate)
            .toHashCode();
    }

    private Action getPrimary() {
        return actions.get(0);
    }

    private Action getRequisite() {
        return actions.get(1);
    }
}
