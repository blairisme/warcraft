/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.spatial.GameObjectNodeSet;
import com.evilbird.warcraft.action.attack.AttackEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;

import javax.inject.Inject;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;

/**
 * A guard task that succeeds when the event queue contains an move or attack
 * event involving the attacker contained in the tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class AttackTrigger extends LeafTask<AttackData>
{
    private Events events;

    @Inject
    public AttackTrigger(Events events) {
        this.events = events;
    }

    @Override
    public Status execute() {
        AttackData data = getObject();
        OffensiveObject attacker = data.getAttacker();
        GameObjectNodeSet positions = data.getAttackablePositions();
        return AttackStatus.isValidAttacker(attacker) && eventsInvolveAttacker(attacker, positions) ? SUCCEEDED : FAILED;
    }

    protected boolean eventsInvolveAttacker(OffensiveObject attacker, GameObjectNodeSet attackableLocations) {
        return moveInvolvesAttacker(attacker, attackableLocations) || attackInvolvesAttacker(attacker);
    }

    protected boolean moveInvolvesAttacker(OffensiveObject attacker, GameObjectNodeSet locations) {
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            GameObject subject = event.getSubject();
            GameObjectNode location = event.getLocation();

            if (event.isFinished() && subject == attacker) {
                return true;
            }
            if (event.isUpdate() && locations.contains(location) && AttackStatus.isValidTarget(attacker, subject)) {
                return true;
            }
        }
        return false;
    }

    protected boolean attackInvolvesAttacker(OffensiveObject attacker) {
        for (AttackEvent event: events.getEvents(AttackEvent.class)) {
            GameObject subject = event.getSubject();
            if (event.isFinished() && subject == attacker) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected Task<AttackData> copyTo(Task<AttackData> task) {
        return task;
    }
}
