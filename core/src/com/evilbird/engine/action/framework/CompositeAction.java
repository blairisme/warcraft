/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.inject.PooledObject;
import com.evilbird.engine.common.serialization.SerializedInitializer;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.engine.object.GameObjectReferencer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Instances of this {@link Action Action} provide common implementation
 * for those actions which represent collection of child actions.
 *
 * @author Blair Butterworth
 */
public abstract class CompositeAction extends AbstractAction
{
    protected transient List<Action> actions;

    public CompositeAction() {
        actions = new ArrayList<>();
    }

    public CompositeAction(List<Action> actions) {
        this.actions = new ArrayList<>(actions);
    }

    public CompositeAction(Action... actions) {
        this.actions = new ArrayList<>(actions.length);
        Collections.addAll(this.actions, actions);
    }

    public boolean isEmpty() {
        return actions.isEmpty();
    }

    public Collection<Action> getActions() {
        return Collections.unmodifiableList(actions);
    }

    protected Action add(Action action) {
        actions.add(action);
        return action;
    }

    protected Action get(int index) {
        return actions.get(index);
    }

    @Override
    public void cancel() {
        super.cancel();
        for (Action delegate: actions) {
            delegate.cancel();
        }
    }

    @Override
    public void setCause(UserInput cause) {
        super.setCause(cause);
        for (Action delegate: actions) {
            delegate.setCause(cause);
        }
    }

    @Override
    public void setSubject(GameObject gameObject) {
        super.setSubject(gameObject);
        for (Action delegate: actions) {
            delegate.setSubject(gameObject);
        }
    }

    @Override
    public void setTarget(GameObject target) {
        super.setTarget(target);
        for (Action delegate: actions) {
            delegate.setTarget(target);
        }
    }

    @Override
    public void setContainer(GameObjectComposite root) {
        super.setContainer(root);
        for (Action action: actions) {
            if (action instanceof GameObjectReferencer){
                GameObjectReferencer referencer = (GameObjectReferencer)action;
                referencer.setContainer(root);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        for (Action delegate: actions) {
            if (delegate instanceof PooledObject) {
                PooledObject<?> pooled = (PooledObject<?>)delegate;
                pooled.reset();
            }
        }
    }

    @Override
    public void restart() {
        super.restart();
        for (Action delegate: actions) {
            delegate.restart();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("base")
            .append("actions", actions)
            .toString();
    }

    @SerializedInitializer
    protected void initialize() {
        for (Action child: actions) {
            if (child instanceof AbstractAction) {
                AbstractAction action = (AbstractAction)child;
                action.setSubjectReference(this.getItemReference());
                action.setTargetReference(this.getTargetReference());
            }
        }
    }
}
