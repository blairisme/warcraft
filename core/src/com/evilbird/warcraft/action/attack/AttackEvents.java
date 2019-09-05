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
    private AttackEvents() {
    }

    public static void attackStarted(Events events, Item attacker, Item target) {
        addEvent(events, attacker, target, Started);
    }

    public static void attackStopped(Events events, Item attacker, Item target) {
        addEvent(events, attacker, target, Stopped);
    }

    public static void attackComplete(Events events, Item attacker, Item target) {
        addEvent(events, attacker, target, Complete);
    }

    public static void attackFailed(Events events, Item attacker, Item target) {
        addEvent(events, attacker, target, Failed);
    }

    public static void attackCancelled(Events events, Item attacker, Item target) {
        addEvent(events, attacker, target, Cancelled);
    }

    public static void addEvent(Events events, Item attacker, Item target, AttackStatus status) {
        events.add(new AttackEvent(attacker, target, status));
    }
}
