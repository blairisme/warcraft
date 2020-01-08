/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.attack.AttackStatus.Cancelled;
import static com.evilbird.warcraft.action.attack.AttackStatus.Complete;
import static com.evilbird.warcraft.action.attack.AttackStatus.Failed;
import static com.evilbird.warcraft.action.attack.AttackStatus.Started;

/**
 * Helper class for generating attack events.
 *
 * @author Blair Butterworth
 */
public class AttackEvents
{
    private transient Events events;

    @Inject
    public AttackEvents(Events events) {
        this.events = events;
    }

    public void attack(GameObject attacker, GameObject target) {
        addEvent(attacker, target, Started);
        addEvent(attacker, target, Complete);
    }

    public void attackStarted(GameObject attacker, GameObject target) {
        addEvent(attacker, target, Started);
    }

    public void attackComplete(GameObject attacker, GameObject target) {
        addEvent(attacker, target, Complete);
    }

    public void attackFailed(GameObject attacker, GameObject target) {
        addEvent(attacker, target, Failed);
    }

    public void attackCancelled(GameObject attacker, GameObject target) {
        addEvent(attacker, target, Cancelled);
    }

    private void addEvent(GameObject attacker, GameObject target, AttackStatus status) {
        events.add(new AttackEvent(attacker, target, status));
    }
}
