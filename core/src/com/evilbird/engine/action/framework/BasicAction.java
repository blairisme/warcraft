/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.ActionStatus;
import com.evilbird.engine.common.inject.PooledObject;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.evilbird.engine.action.ActionStatus.Failed;
import static com.evilbird.engine.action.ActionStatus.Fresh;
import static com.evilbird.engine.action.ActionStatus.Running;
import static com.evilbird.engine.action.ActionStatus.Succeeded;
import static com.evilbird.engine.action.ActionStatus.forResult;

/**
 * Instances of this class represent a base class for {@link Action Actions},
 * containing common methods and properties utilised by many custom actions.
 *
 * @author Blair Butterworth
 */
public abstract class BasicAction extends AbstractAction
{
    private static final transient Logger logger = LoggerFactory.getLogger(EventQueue.class);

    private ActionStatus status;

    private transient String error;

    public BasicAction() {
        status = Fresh;
    }

    /**
     * Updates the Action based on time. Typically this is called each frame by
     * {@link GameObject#update(float)}.
     *
     * @param time  the number of seconds since the last invocation.
     * @return      {@code true} if the action is done.
     */
    public abstract ActionResult act(float time);

    /**
     * Updates the Action based on time. Typically this is called each frame by
     * {@link GameObject#update(float)}.
     */
    @Override
    public boolean run(float time) {
        if (status == Fresh) {
            start();
        }
        if (status == Running) {
            status = forResult(act(time));
        }
        if (status == Succeeded) {
            end();
        }
        if (status == Failed) {
            failed();
        }
        return status != Running;
    }

    public void start() {
        status = Running;
        logger.debug("Action '{}' started", identifier);
    }

    public void end() {
        logger.debug("Action '{}' ended", identifier);
    }

    public void failed() {
        logger.debug("Action '{}' failed - {}", identifier, error);
    }

    /**
     * Resets the object for reuse. Object references should be set to
     * {@code null} and fields may be set to default values.
     */
    @Override
    public void reset() {
        super.reset();
        status = Fresh;
        logger.debug("Action '{}' reset", identifier);
    }

    /**
     * Sets the state of the action so that it can be run again.
     */
    @Override
    public void restart() {
        super.restart();
        status = Running;
        logger.debug("Action '{}' restarted", identifier);
    }

    /**
     * Frees any resources held by the action. If the action is a
     * {@link PooledObject} then the action is returned to the pool.
     */
    @Override
    public void cancel() {
        super.cancel();
        logger.debug("Action '{}' cancelled", identifier);
    }

    public ActionStatus getStatus() {
        return status;
    }

    @Override
    public boolean isFailed() {
        return status == Failed;
    }

    public void setFailed(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("identifier", identifier)
            .append("status", status)
            .append("subject", item)
            .append("target", target)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        BasicAction that = (BasicAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(status, that.status)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(status)
            .toHashCode();
    }
}
