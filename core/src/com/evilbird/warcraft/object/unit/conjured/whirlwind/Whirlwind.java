/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.conjured.whirlwind;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAreaEffect;

import static org.apache.commons.lang3.RandomUtils.nextFloat;

/**
 * A game object that represents the Whirlwind area of effect game object.
 *
 * @author Blair Butterworth
 */
public class Whirlwind extends ConjuredAreaEffect
{
    private static final transient float WHIRLWIND_SPEED = 32f;
    private static final transient float REPOSITION_RADIUS = 128f;
    private static final transient float REPOSITION_INTERVAL_MIN = 1f;
    private static final transient float REPOSITION_INTERVAL_MAX = 4f;

    private transient GameTimer timer;
    private transient Vector2 destination;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * a {@link AnimatedObjectStyle}, specifying the visual and auditory presentation
     * of the Whirlwind object.
     */
    public Whirlwind(Skin skin) {
        super(skin);
        timer = new GameTimer(0);
    }

    @Override
    public void update(float time) {
        super.update(time);
        updateDestination(time);
        updatePosition(time);
    }

    private void updateDestination(float time) {
        if (timer.advance(time)) {
            GameObjectContainer root = getRoot();
            GameObjectGraph graph = root.getSpatialGraph();

            Vector2 worldSize = graph.getGraphSize();
            Vector2 position = getPosition();

            Vector2 start = new Vector2(
                Math.max(0, position.x - REPOSITION_RADIUS),
                Math.max(0, position.y - REPOSITION_RADIUS));
            Vector2 end = new Vector2(
                Math.min(worldSize.x, position.x + REPOSITION_RADIUS),
                Math.min(worldSize.y, position.y + REPOSITION_RADIUS));

            destination = new Vector2(nextFloat(start.x, end.x), nextFloat(start.y, end.y));
            timer = new GameTimer(nextFloat(REPOSITION_INTERVAL_MIN, REPOSITION_INTERVAL_MAX));
        }
    }

    private void updatePosition(float time) {
        Vector2 position = getPosition();
        Vector2 remaining = destination.cpy().sub(position);
        float remainingDistance = remaining.len();
        float incrementDistance = time * WHIRLWIND_SPEED;

        if (remainingDistance > incrementDistance) {
            Vector2 direction = remaining.nor();
            Vector2 increment = direction.scl(incrementDistance);
            position.add(increment);
        } else {
            position.set(destination);
            timer.end();
        }
        setPosition(position);
    }
}
