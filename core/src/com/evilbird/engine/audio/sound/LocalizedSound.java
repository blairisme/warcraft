/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.sound;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;

import java.util.Objects;

/**
 * Represents a sound effect played only while its owner is on screen.
 * Specifically the volume of a given {@code Sound} will be set to max if on
 * screen and silent if off screen.
 *
 * @author Blair Butterworth
 */
public class LocalizedSound implements Sound
{
    private Sound sound;
    private GameObject owner;
    private Camera camera;
    private float volume;

    /**
     * Constructs a new instance of this class given the {@link Sound} that
     * will be played when the {@link GameObject} that owns it is displayed on
     * screen.
     *
     * @param sound     a {@code Sound}.
     * @param owner     an {@code Item} that owns the sound.
     *
     * @throws NullPointerException if the given sound or owner are
     *                              {@code null}.
     */
    public LocalizedSound(Sound sound, GameObject owner) {
        Objects.requireNonNull(sound);
        Objects.requireNonNull(owner);

        this.sound = sound;
        this.owner = owner;
        this.volume = 1;
    }

    @Override
    public void dispose() {
        sound.dispose();
    }

    @Override
    public boolean isPlaying() {
        return sound.isPlaying();
    }

    @Override
    public void play() {
        sound.play();
    }

    @Override
    public void stop() {
        sound.stop();
    }

    @Override
    public void setVolume(float volume) {
        this.volume = volume;
    }

    /**
     * Checks to see if the owner is on-screen, setting the corresponding
     * volume of the sound.
     */
    public void update() {
        Vector2 position = owner.getPosition();
        if (onCamera(position)) {
            sound.setVolume(volume);
        } else {
            sound.setVolume(0);
        }
    }

    protected boolean onCamera(Vector2 position) {
        if (camera == null) {
            camera = getCamera(owner);
        }
        if (camera != null) {
            return camera.frustum.pointInFrustum(position.x, position.y, 0);
        }
        return true;
    }

    protected Camera getCamera(GameObject gameObject) {
        GameObjectContainer root = gameObject.getRoot();
        if (root != null) {
            Viewport viewport = root.getViewport();
            return viewport.getCamera();
        }
        return null;
    }
}


