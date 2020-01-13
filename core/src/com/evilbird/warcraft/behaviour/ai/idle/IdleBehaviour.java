/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.idle;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourElement;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Behaviour that randomly re-orients idle units.
 *
 * @author Blair Butterworth
 */
public class IdleBehaviour implements AiBehaviourElement
{
    private static final float PERIOD = 1f;
    private static final int REORIENT_MIN = 2;
    private static final int REORIENT_MAX = 4;

    private Events events;
    private Random random;
    private GameTimer stopWatch;
    private List<GameObject> movables;

    @Inject
    public IdleBehaviour(Events events) {
        this.events = events;
        this.random = new Random();
        this.stopWatch = new GameTimer(PERIOD);
    }

    @Override
    public void applyBehaviour(GameObjectContainer container, float time) {
        initialize(container);
        update();
        reorient(time);
    }

    private void initialize(GameObjectContainer container) {
        if (movables == null) {
            movables = new ArrayList<>(container.findAll(UnitOperations::isMovable));
        }
    }

    private void update() {
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            GameObject subject = event.getSubject();
            if (UnitOperations.isMovable(subject)) {
                movables.add(subject);
            }
        }
        for (RemoveEvent event: events.getEvents(RemoveEvent.class)) {
            GameObject subject = event.getSubject();
            if (UnitOperations.isMovable(subject)) {
                movables.remove(subject);
            }
        }
    }

    private void reorient(float time) {
        if (stopWatch.advance(time)) {
            stopWatch.reset();
            reorientRandomSelection();
        }
    }

    private void reorientRandomSelection() {
        for (int i = 0; i < REORIENT_MIN + random.nextInt(REORIENT_MAX); i++) {
            MovableObject target = (MovableObject)movables.get(random.nextInt(movables.size()));
            if (UnitOperations.isAlive(target) && GameObjectOperations.isIdle(target)) {
                target.setDirection(getRandomDirection());
            }
        }
    }

    private Vector2 getRandomDirection() {
        Vector2 result = new Vector2(1, 1);
        result.rotate(random.nextInt(360));
        return result;
    }
}
