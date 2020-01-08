/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.conjured.rune;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.common.graphics.animation.AnimationFrame;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.unit.conjured.ConjuredObject;

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
    private static final transient float RUNE_DURATION = 0.6f;
    private static final transient float RUNE_INTERVAL = 4f;
    private static final transient float RUNE_PADDING = 30f;
    private static final transient float RUNE_WIDTH = 16f;
    private static final transient float RUNE_HEIGHT = 16f;

    private transient GameTimer timer;
    private transient Map<Vector2, Float> effects;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * a {@link AnimatedObjectStyle}, specifying the visual and auditory presentation
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
        Animation animation = super.animation;
        for (Entry<Vector2, Float> entry: effects.entrySet()) {
            Vector2 position = entry.getKey();
            AnimationFrame frame = animation.getFrame(entry.getValue());
            frame.draw(batch, position.x, position.y, RUNE_WIDTH, RUNE_HEIGHT);
        }
    }

    private void updateEffects(float time) {
        CollectionUtils.forEach(effects.entrySet(), entry -> entry.setValue(entry.getValue() + time));
    }

    private void updateVisibility(float time) {
        if (timer.advance(time)) {
            timer = new GameTimer(timer.duration() == RUNE_DURATION ? RUNE_INTERVAL : RUNE_DURATION);
            setVisible(! getVisible());
            CollectionUtils.forEach(effects.entrySet(), entry -> entry.setValue(0f));
        }
    }
}
