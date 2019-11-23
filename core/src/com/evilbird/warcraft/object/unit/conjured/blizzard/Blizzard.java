/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured.blizzard;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.common.graphics.animation.AnimationFrame;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAreaEffect;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A game object that represents a Blizzard.
 *
 * @author Blair Butterworth
 */
public class Blizzard extends ConjuredAreaEffect
{
    private static final transient int HALE_COUNT_MIN = 3;
    private static final transient int HALE_COUNT_MAX = 10;
    private static final transient float HALE_INTERVAL_MIN = 0.5f;
    private static final transient float HALE_INTERVAL_MAX = 1.5f;
    private static final transient int HALE_SPEED = 256;
    private static final transient float HALE_WIDTH = 32;
    private static final transient float HALE_HEIGHT = 32;
    private static final transient float HALE_ORIGIN_OFFSET_X = -70;
    private static final transient float HALE_ORIGIN_OFFSET_Y = 150;

    private transient GameTimer timer;
    private transient Map<Vector2, Float> haleTimers;
    private transient Map<Vector2, Vector2> halePositions;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * a {@link AnimatedObjectStyle}, specifying the visual and auditory presentation
     * of the Blizzard.
     */
    public Blizzard(Skin skin, WarcraftPreferences preferences) {
        super(skin, preferences);
        this.halePositions = new HashMap<>(10);
        this.haleTimers = new HashMap<>(10);
        this.timer = new GameTimer(0);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        drawHale(batch);
    }

    @Override
    public void update(float time) {
        super.update(time);
        moveHale(time);
        pruneHale();
        addHale(time);
    }

    private void addHale(float time) {
        if (timer.advance(time)) {
            timer = new GameTimer(RandomUtils.nextFloat(HALE_INTERVAL_MIN, HALE_INTERVAL_MAX));

            Vector2 size = getSize();
            Vector2 start = getPosition();
            Vector2 end = new Vector2(start.x + size.x, start.y + size.y);

            for (int i = 0; i < RandomUtils.nextInt(HALE_COUNT_MIN, HALE_COUNT_MAX); i++) {
                addHale(start, end);
            }
        }
    }

    private void addHale(Vector2 start, Vector2 end) {
        Vector2 destination = new Vector2(
                RandomUtils.nextFloat(start.x, end.x),
                RandomUtils.nextFloat(start.y, end.y));

        Vector2 position = new Vector2(
                destination.x + HALE_ORIGIN_OFFSET_X,
                destination.y + HALE_ORIGIN_OFFSET_Y);

        halePositions.put(destination, position);
        haleTimers.put(destination, 0f);
    }

    private void drawHale(Batch batch) {
        Animation animation = super.animation;
        for (Entry<Vector2, Vector2> entry: halePositions.entrySet()) {
            Vector2 position = entry.getValue();
            Float time = haleTimers.get(entry.getKey());
            AnimationFrame frame = animation.getFrame(time);
            frame.draw(batch, position.x, position.y, HALE_WIDTH, HALE_HEIGHT);
        }
    }

    private void moveHale(float time) {
        for (Entry<Vector2, Vector2> entry: halePositions.entrySet()) {
            moveHale(entry.getValue(), entry.getKey(), time);
        }
        for (Entry<Vector2, Float> entry: haleTimers.entrySet()) {
            entry.setValue(entry.getValue() + time);
        }
    }

    private void moveHale(Vector2 position, Vector2 destination, float time) {
        Vector2 remaining = destination.cpy().sub(position);
        float remainingDistance = remaining.len();
        float incrementDistance = time * HALE_SPEED;

        if (remainingDistance > incrementDistance) {
            Vector2 direction = remaining.nor();
            Vector2 increment = direction.scl(incrementDistance);
            position.add(increment);
        } else {
            position.set(destination);
        }
    }

    private void pruneHale() {
        Iterator<Entry<Vector2, Vector2>> iterator = halePositions.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Vector2, Vector2> entry = iterator.next();
            if (entry.getKey().equals(entry.getValue())) {
                haleTimers.remove(entry.getKey());
                iterator.remove();
            }
        }
    }
}
