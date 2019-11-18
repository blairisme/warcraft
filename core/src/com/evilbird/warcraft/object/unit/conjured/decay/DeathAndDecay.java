/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured.decay;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.common.graphics.animation.AnimationFrame;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.unit.conjured.ConjuredObject;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * A game object that represents the death and decay area of effect game object.
 *
 * @author Blair Butterworth
 */
public class DeathAndDecay extends ConjuredObject
{
    private static final transient int DECAY_COUNT_MIN = 3;
    private static final transient int DECAY_COUNT_MAX = 10;
    private static final transient float DECAY_INTERVAL_MIN = 0.5f;
    private static final transient float DECAY_INTERVAL_MAX = 1.5f;
    private static final transient float DECAY_DURATION = 1.5f;
    private static final transient float DECAY_WIDTH = 16f;
    private static final transient float DECAY_HEIGHT = 16f;

    private transient GameTimer timer;
    private transient Map<Vector2, Float> effects;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * a {@link AnimatedObjectStyle}, specifying the visual and auditory presentation
     * of the Death and Decay object.
     */
    public DeathAndDecay(Skin skin) {
        super(skin);
        this.timer = new GameTimer(0);
        this.effects = new HashMap<>(10);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        drawEffects(batch);
    }

    @Override
    public void update(float time) {
        super.update(time);
        updateEffects(time);
        pruneEffects();
        addEffects(time);
    }

    private void addEffects(float time) {
        if (timer.advance(time)) {
            timer = new GameTimer(RandomUtils.nextFloat(DECAY_INTERVAL_MIN, DECAY_INTERVAL_MAX));

            Vector2 size = getSize();
            Vector2 start = getPosition();
            Vector2 end = new Vector2(start.x + size.x, start.y + size.y);

            for (int i = 0; i < RandomUtils.nextInt(DECAY_COUNT_MIN, DECAY_COUNT_MAX); i++) {
                addEffect(start, end);
            }
        }
    }

    private void addEffect(Vector2 start, Vector2 end) {
        Vector2 position = new Vector2(
            RandomUtils.nextFloat(start.x, end.x),
            RandomUtils.nextFloat(start.y, end.y));
        effects.put(position, 0f);
    }

    private void drawEffects(Batch batch) {
        Animation animation = super.animation;
        for (Map.Entry<Vector2, Float> entry: effects.entrySet()) {
            Vector2 position = entry.getKey();
            AnimationFrame frame = animation.getFrame(entry.getValue());
            frame.draw(batch, position.x, position.y, DECAY_WIDTH, DECAY_HEIGHT);
        }
    }

    private void pruneEffects() {
        effects.entrySet().removeIf(entry -> entry.getValue() >= DECAY_DURATION);
    }

    private void updateEffects(float time) {
        effects.entrySet().forEach(entry -> entry.setValue(entry.getValue() + time));
    }
}
