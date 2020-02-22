/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.utils.Pool;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionStatus;
import com.evilbird.engine.common.inject.PooledObject;
import com.evilbird.engine.common.lang.GenericIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.engine.object.GameObjectReference;
import com.evilbird.engine.object.GameObjectReferencer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.engine.action.ActionStatus.Fresh;
import static com.evilbird.engine.action.ActionStatus.Running;
import static com.evilbird.engine.action.ActionStatus.Succeeded;

/**
 * Instances of this class represent a base class for {@link Action Actions},
 * containing common methods and properties utilised by many custom actions.
 *
 * @author Blair Butterworth
 */
public abstract class AbstractAction implements Action, GameObjectReferencer, PooledObject<Action>
{
    private Identifier identifier;
    private UserInput cause;

    private GameObjectReference<GameObject> item;
    private GameObjectReference<GameObject> target;

    private transient ActionStatus status;
    private transient Pool<Action> pool;

    public AbstractAction() {
        status = Fresh;
    }

    /**
     * Updates the Action based on time. Typically this is called each frame by
     * {@link GameObject#update(float)}.
     *
     * @param time  the number of seconds since the last invocation.
     * @return      {@code true} if the action is done.
     */
    public abstract boolean act(float time);

    @Override
    public boolean run(float time) {
        if (status == Fresh) {
            start();
        }
        if (status == Running) {
            status = act(time) ? Succeeded : Running;
        }
        if (status == Succeeded) {
            end();
        }
        return status != Running;
    }

    public void start() {
        status = Running;
    }

    public void end() {
    }

    /**
     * Resets the object for reuse. Object references should be set to
     * {@code null} and fields may be set to default values.
     */
    public void reset() {
        status = Fresh;
    }

    /**
     * Sets the state of the action so that it can be run again.
     */
    public void restart() {
        status = Running;
    }

    /**
     * Frees any resources held by the action. If the action is a
     * {@link PooledObject} then the action is returned to the pool.
     */
    @Override
    public void cancel() {
        Pool<Action> pool = getPool();
        if (pool != null) {
            pool.free(this);
        }
    }

    @Override
    public UserInput getCause() {
        return cause;
    }

    @Override
    public Identifier getIdentifier() {
        if (identifier == null) {
            return GenericIdentifier.Unknown;
        }
        return identifier;
    }

    @Override
    public Pool<Action> getPool() {
        return pool;
    }

    @Override
    public GameObject getSubject() {
        return item != null ? item.get() : null;
    }

    public GameObjectReference<GameObject> getItemReference() {
        return item;
    }

    @Override
    public GameObject getTarget() {
        return target != null ? target.get() : null;
    }

    public GameObjectReference<GameObject> getTargetReference() {
        return target;
    }

    @Override
    public ActionStatus getStatus() {
        return status;
    }

    @Override
    public void setCause(UserInput cause) {
        this.cause = cause;
    }

    /**
     * Set the actions unique identifier.
     */
    @Override
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public void setPool(Pool<Action> pool) {
        this.pool = pool;
    }

    @Override
    public void setSubject(GameObject gameObject) {
        this.item = gameObject != null ? new GameObjectReference<>(gameObject) : null;
    }

    public void setSubjectReference(GameObjectReference<GameObject> reference) {
        this.item = reference;
    }

    @Override
    public void setTarget(GameObject target) {
        this.target = target != null ? new GameObjectReference<>(target) : null;
    }

    public void setTargetReference(GameObjectReference<GameObject> reference) {
        this.target = reference;
    }

    @Override
    public void setContainer(GameObjectComposite root) {
        if (item != null) {
            item.setParent(root);
        }
        if (target != null) {
            target.setParent(root);
        }
    }

    public void setFailed(String message) {
        //TODO
        status = ActionStatus.Failed;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("identifier", identifier)
            .append("cause", cause)
            .append("subject", item)
            .append("target", target)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        AbstractAction that = (AbstractAction)obj;
        return new EqualsBuilder()
            .append(identifier, that.identifier)
            .append(cause, that.cause)
            .append(item, that.item)
            .append(target, that.target)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(identifier)
            .append(cause)
            .append(item)
            .append(target)
            .toHashCode();
    }
}
