/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.object.GameObject;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are generated when one {@link GameObject} is
 * instructed to attack another.
 *
 * @author Blair Butterworth
 */
public class AttackEvent implements Event
{
    private GameObject subject;
    private GameObject target;
    private AttackStatus status;

    public AttackEvent(GameObject subject, GameObject target, AttackStatus status) {
        this.subject = subject;
        this.target = target;
        this.status = status;
    }

    @Override
    public GameObject getSubject() {
        return subject;
    }

    public GameObject getTarget() {
        return target;
    }

    public AttackStatus getStatus() {
        return status;
    }

    public boolean isStarting() {
        return status == AttackStatus.Started;
    }

    public boolean isFinished() {
        return status == AttackStatus.Complete
            || status == AttackStatus.Failed
            || status == AttackStatus.Cancelled;
    }

    public boolean isFailed() {
        return status == AttackStatus.Failed;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("subject", subject.getIdentifier())
            .append("target", target.getIdentifier())
            .append("status", status)
            .toString();
    }
}
