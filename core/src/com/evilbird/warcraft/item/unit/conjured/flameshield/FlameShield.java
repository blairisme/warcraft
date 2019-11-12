/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured.flameshield;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.AnimationFrame;
import com.evilbird.engine.common.graphics.AnimationRenderer;
import com.evilbird.engine.object.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.conjured.ConjuredObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A game object that represents the Flame Shield game object.
 *
 * @author Blair Butterworth
 */
public class FlameShield extends ConjuredObject
{
    private static transient final int COUNT = 5;
    private static transient final float SPEED = 90f;
    private static transient final float WIDTH = 16f;
    private static transient final float HEIGHT = 16f;

    private transient List<Vector2> positions;
    private transient List<Float> rotations;
    private transient List<Float> times;
    private transient Vector2 center;
    private transient Animation animation;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * a {@link ViewableStyle}, specifying the visual and auditory presentation
     * of the Flame Shield object.
     */
    public FlameShield(Skin skin) {
        super(skin);
        this.positions = new ArrayList<>(COUNT);
        this.rotations = new ArrayList<>(COUNT);
        this.times = new ArrayList<>(COUNT);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        drawEffects((SpriteBatch)batch);
    }

    @Override
    public void update(float time) {
        super.update(time);
        initializeEffects();
        updateEffects(time);
        rotateEffects(time);
    }

    private void initializeEffects() {
        if (positions.isEmpty()) {
            initializeAnimation();
            initializePositions();
            initializeRotations();
            initializeTimes();
        }
    }

    private void initializeAnimation() {
        Vector2 size = getSize();
        center = size.scl(0.5f);

        AnimationRenderer renderer = super.animation;
        animation = renderer.getAnimation();
    }

    private void initializePositions() {
        Vector2 start = getPosition();
        positions.add(start);
        positions.add(start);
        positions.add(start);
        positions.add(start);
        positions.add(start);
    }

    private void initializeRotations() {
        rotations.add(0f);
        rotations.add(72f);
        rotations.add(144f);
        rotations.add(216f);
        rotations.add(288f);
    }

    private void initializeTimes() {
        times.add(0f);
        times.add(0.1f);
        times.add(0.2f);
        times.add(0.3f);
        times.add(0.4f);
    }

    private void drawEffects(SpriteBatch batch) {
        if (! positions.isEmpty()) {
            for (int index = 0; index < COUNT; index++) {
                Vector2 position = positions.get(index);
                float rotation = rotations.get(index);
                float time = times.get(index);

                AnimationFrame frame = animation.getFrame(time);
                TextureRegionDrawable drawable = (TextureRegionDrawable) frame.getDrawable();
                TextureRegion region = drawable.getRegion();

                batch.draw(region, position.x, position.y, center.x, center.y, WIDTH, HEIGHT, 1f, 1f, rotation, true);
            }
        }
    }

    private void updateEffects(float time) {
        for (int index = 0; index < COUNT; index++) {
            float oldTime = times.get(index);
            float newTime = oldTime + time;
            times.set(index, newTime);
        }
    }

    private void rotateEffects(float time) {
        for (int index = 0; index < COUNT; index++) {
            float increment = SPEED * time;
            float oldRotation = rotations.get(index);
            float newRotation = oldRotation + increment;
            if (newRotation > 360) {
                newRotation -= 360;
            }
            rotations.set(index, newRotation);
        }
    }
}
