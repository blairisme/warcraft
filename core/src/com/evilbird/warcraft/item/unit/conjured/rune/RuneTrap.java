/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured.rune;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.AnimationFrame;
import com.evilbird.engine.common.graphics.AnimationRenderer;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.conjured.ConjuredObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A game object that represents the Rune Trap game object.
 *
 * @author Blair Butterworth
 */
public class RuneTrap extends ConjuredObject
{
    private static transient final float RUNE_DURATION = 0.6f;
    private static transient final float RUNE_INTERVAL = 4f;
    private static transient final float RUNE_PADDING = 30f;
    private static transient final float RUNE_WIDTH = 16f;
    private static transient final float RUNE_HEIGHT = 16f;

    private transient GameTimer timer;
    private transient Map<Vector2, Float> effects;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * a {@link ViewableStyle}, specifying the visual and auditory presentation
     * of the Rune Trap object.
     */
    public RuneTrap(Skin skin) {
        super(skin);
        this.setVisible(false);
        this.timer = new GameTimer(RUNE_INTERVAL);
        this.effects = new HashMap<>(5);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        drawEffects(batch);
    }

    @Override
    public void update(float time) {
        super.update(time);
        initializeEffects();
        updateEffects(time);
        updateVisibility(time);
    }

    private void initializeEffects() {
        if (effects.isEmpty()) {
            Vector2 size = getSize();
            Vector2 radius = size.scl(0.5f);
            Vector2 start = getPosition();
            Vector2 center = new Vector2(start.x + radius.x - 8, start.y + radius.y - 8);

            effects.put(center, 0f);
            effects.put(new Vector2(center.x, center.y + RUNE_PADDING), 0f);
            effects.put(new Vector2(center.x - RUNE_PADDING, center.y), 0f);
            effects.put(new Vector2(center.x + RUNE_PADDING, center.y), 0f);
            effects.put(new Vector2(center.x, center.y - RUNE_PADDING), 0f);
        }
    }

    private void drawEffects(Batch batch) {
        AnimationRenderer renderer = super.animation;
        Animation animation = renderer.getAnimation();

        for (Entry<Vector2, Float> entry: effects.entrySet()) {
            Vector2 position = entry.getKey();
            AnimationFrame frame = animation.getFrame(entry.getValue());
            frame.draw(batch, position.x, position.y, RUNE_WIDTH, RUNE_HEIGHT);
        }
    }

    private void updateEffects(float time) {
        effects.entrySet().forEach(entry -> entry.setValue(entry.getValue() + time));
    }

    private void updateVisibility(float time) {
        if (timer.advance(time)) {
            timer = new GameTimer(timer.duration() == RUNE_DURATION ? RUNE_INTERVAL : RUNE_DURATION);
            setVisible(! getVisible());
            effects.entrySet().forEach(entry -> entry.setValue(0f));
        }
    }
}
