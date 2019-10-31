/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are generated when one {@link Item} is
 * instructed to attack another.
 *
 * @author Blair Butterworth
 */
public class AttackEvent implements Event
{
    private Item subject;
    private Item target;
    private AttackStatus status;

    public AttackEvent(Item subject, Item target, AttackStatus status) {
        this.subject = subject;
        this.target = target;
        this.status = status;
    }

    @Override
    public Item getSubject() {
        return subject;
    }

    public Item getTarget() {
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
            || status == AttackStatus.Cancelled
            || status == AttackStatus.Stopped;
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
