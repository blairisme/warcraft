/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured.flameshield;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.animation.AnimationFrame;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectReference;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAreaEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * A game object that represents the Flame Shield game object.
 *
 * @author Blair Butterworth
 */
public class FlameShield extends ConjuredAreaEffect
{
    private static final transient int COUNT = 5;
    private static final transient float SPEED = 90f;
    private static final transient float WIDTH = 16f;
    private static final transient float HEIGHT = 16f;

    private GameObjectReference<GameObject> target;

    private transient List<Float> rotations;
    private transient List<Float> times;
    private transient Vector2 center;
    private transient Vector2 offset;
    private transient Vector2 location;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * a {@link AnimatedObjectStyle}, specifying the visual and auditory presentation
     * of the Flame Shield object.
     */
    public FlameShield(Skin skin, WarcraftPreferences preferences) {
        super(skin, preferences);
        this.rotations = new ArrayList<>(COUNT);
        this.times = new ArrayList<>(COUNT);
    }

    /**
     * Sets the recipient of the flame shield.
     */
    public void setTarget(GameObject target) {
        Vector2 size = target.getSize();
        this.target = new GameObjectReference<>(target);
        this.center = new Vector2(size.x, size.y);
        this.offset = new Vector2(size.x * 0.5f, size.y * 0.5f);
    }

    /**
     * Renders the flame shield.
     */
    @Override
    public void draw(Batch batch, float alpha) {
        if (! rotations.isEmpty()) {
            for (int index = 0; index < COUNT; index++) {
                float rotation = rotations.get(index);
                float time = times.get(index);
                TextureRegion frame = getFrame(time);
                batch.draw(frame, location.x, location.y, center.x, center.y, WIDTH, HEIGHT, 1f, 1f, rotation, true);
            }
        }
    }

    @Override
    public void update(float time) {
        super.update(time);
        initializeEffects();
        updatePosition();
        updateFrames(time);
        updateRotations(time);
    }

    private void initializeEffects() {
        if (rotations.isEmpty()) {
            initializeRotations();
            initializeTimes();
        }
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

    private void updatePosition() {
        if (target != null) {
            GameObject object = target.get();
            location = object.getPosition();
            setPosition(location);
            location.sub(offset);
        }
    }

    private void updateFrames(float time) {
        for (int index = 0; index < COUNT; index++) {
            float oldTime = times.get(index);
            float newTime = oldTime + time;
            times.set(index, newTime);
        }
    }

    private void updateRotations(float time) {
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

    private TextureRegion getFrame(float time) {
        AnimationFrame frame = animation.getFrame(time);
        TextureRegionDrawable drawable = (TextureRegionDrawable)frame.getDrawable();
        return drawable.getRegion();
    }
}
