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
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourElement;
import com.evilbird.warcraft.object.unit.Unit;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.either;
import static com.evilbird.engine.object.utility.GameObjectPredicates.isIdle;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isCombatant;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isCritter;

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

    private Random random;
    private GameTimer stopWatch;

    @Inject
    public IdleBehaviour() {
        random = new Random();
        stopWatch = new GameTimer(PERIOD);
    }

    @Override
    public void applyBehaviour(GameObjectContainer root, float time) {
        if (stopWatch.advance(time)) {
            stopWatch.reset();
            reorient(root);
        }
    }

    private void reorient(GameObjectContainer root) {
        for (int i = 0; i < REORIENT_MIN + random.nextInt(REORIENT_MAX); i++) {
            Unit target = getTarget(root);
            target.setDirection(getDirection());
        }
    }

    private Unit getTarget(GameObjectContainer root) {
        Predicate<GameObject> targets = either(isCombatant(), isCritter()).and(isIdle()).and(isAlive());
        List<GameObject> gameObjects =  new ArrayList<>(root.findAll(targets));
        return (Unit) gameObjects.get(random.nextInt(gameObjects.size()));
    }

    private Vector2 getDirection() {
        Vector2 result = new Vector2(1, 1);
        result.rotate(random.nextInt(360));
        return result;
    }
}
