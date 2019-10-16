/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.attack.AttackStatus.Cancelled;
import static com.evilbird.warcraft.action.attack.AttackStatus.Complete;
import static com.evilbird.warcraft.action.attack.AttackStatus.Failed;
import static com.evilbird.warcraft.action.attack.AttackStatus.Started;
import static com.evilbird.warcraft.action.attack.AttackStatus.Stopped;

/**
 * Helper class for generating attack events.
 *
 * @author Blair Butterworth
 */
public class AttackEvents
{
    private Events events;

    @Inject
    public AttackEvents(Events events) {
        this.events = events;
    }

    public void attackStarted(Item attacker, Item target) {
        addEvent(attacker, target, Started);
    }

    public void attackStopped(Item attacker, Item target) {
        addEvent(attacker, target, Stopped);
    }

    public void attackComplete(Item attacker, Item target) {
        addEvent(attacker, target, Complete);
    }

    public void attackFailed(Item attacker, Item target) {
        addEvent(attacker, target, Failed);
    }

    public void attackCancelled(Item attacker, Item target) {
        addEvent(attacker, target, Cancelled);
    }

    public void attackFinished(Item attacker, Item target, boolean failed) {
        if (failed) {
            attackFailed(attacker, target);
        } else {
            attackComplete(attacker, target);
        }
    }

    public void addEvent(Item attacker, Item target, AttackStatus status) {
        events.add(new AttackEvent(attacker, target, status));
    }
}
