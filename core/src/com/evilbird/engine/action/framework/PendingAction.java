/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.time.GameTimer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Represents an {@link Action} that will be invoked after a given period of
 * time.
 *
 * @author Blair Butterworth
 */
public class PendingAction
{
    private Action action;
    private GameTimer timer;

    /**
     * Constructs a new instance of this class given an action that will be
     * invoked with the given timer is complete.
     */
    public PendingAction(Action action, GameTimer timer) {
        this.action = action;
        this.timer = timer;
    }

    /**
     * Returns the action whose execution is pending.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Returns a timer that when complete will signal that execution of the
     * pending action should commence.
     */
    public GameTimer getTimer() {
        return timer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        PendingAction that = (PendingAction)obj;
        return new EqualsBuilder()
            .append(action, that.action)
            .append(timer, that.timer)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(action)
            .append(timer)
            .toHashCode();
    }
}
