/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.idle;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourElement;
import com.evilbird.warcraft.item.unit.Unit;
import org.apache.commons.lang3.time.StopWatch;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.either;
import static com.evilbird.engine.object.utility.GameObjectPredicates.isIdle;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCombatant;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCritter;

/**
 * Behaviour that randomly re-orients idle units.
 *
 * @author Blair Butterworth
 */
public class IdleBehaviour implements AiBehaviourElement
{
    private static final float PERIOD = 0.5f;
    private static final int REORIENT_MIN = 2;
    private static final int REORIENT_MAX = 4;

    private Random random;
    private StopWatch stopWatch;

    @Inject
    public IdleBehaviour() {
        random = new Random();
        stopWatch = StopWatch.createStarted();
    }

    @Override
    public void applyBehaviour(GameObjectContainer root) {
        if (stopWatch.getTime(TimeUnit.SECONDS) >= PERIOD) {
            stopWatch.reset();
            reorient(root);
            stopWatch.start();
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
