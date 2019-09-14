/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.sound;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.Item;

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
    private Item owner;
    private Camera camera;
    private float volume;

    /**
     * Constructs a new instance of this class given the {@link Sound} that
     * will be played when the {@link Item} that owns it is displayed on
     * screen.
     *
     * @param sound     a {@code Sound}.
     * @param owner     an {@code Item} that owns the sound.
     * @param camera    a {@link Camera} that determines what is shown on
     *                  screen.
     *
     * @throws NullPointerException if the given sound, owner or camera are
     *                              {@code null}.
     */
    public LocalizedSound(Sound sound, Item owner, Camera camera) {
        Objects.requireNonNull(sound);
        Objects.requireNonNull(owner);
        Objects.requireNonNull(camera);

        this.sound = sound;
        this.owner = owner;
        this.camera = camera;
        this.volume = 1;
    }

    @Override
    public void play() {
        sound.play();
    }

    @Override
    public void pause() {
        sound.pause();
    }

    @Override
    public void resume() {
        sound.resume();
    }

    @Override
    public void stop() {
        sound.stop();
    }

    /**
     * Sets the volume of the playing sound. The new volume will be applied on
     * the next {@link LocalizedSound#update()}.
     *
     * @param volume the volume in the range 0 (silent) to 1 (max volume).
     */
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
        return camera.frustum.pointInFrustum(position.x, position.y, 0);
    }
}