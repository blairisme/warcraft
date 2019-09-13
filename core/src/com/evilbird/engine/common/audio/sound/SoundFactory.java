/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.sound;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Provides utility functions for working with sound effects.
 *
 * @author Blair Butterworth
 */
public class SoundFactory
{
    /**
     * Disable construction of static utility class.
     */
    private SoundFactory() {
    }

    /**
     * Creates a new {@link Sound} instance using the asset identified by the
     * given path and managed by the specified {@link AssetManager}.
     *
     * @param assets    an {@code AssetManager} the contains the desired sound.
     * @param path      the path of the desired sound.
     * @return          a new {@code Sound} instance.
     *
     * @throws NullPointerException if the given {@code AssetManager} or path
     *                              are {@code null}.
     * @throws GdxRuntimeException  if the asset identified by the given path
     *                              isn't managed by the {@code AssetManager}
     *                              or hasn't been loaded.
     */
    public static Sound newSound(AssetManager assets, String path) {
        Objects.requireNonNull(assets);
        Objects.requireNonNull(path);
        return new BasicSound(assets.get(path, com.badlogic.gdx.audio.Sound.class));
    }

    /**
     * Creates a new {@link Sound} instance containing the sound assets
     * identified by the given paths and managed by the given
     * {@link AssetManager}. The resulting sound will play one of these sounds
     * at random when played.
     *
     * @param assets    an {@code AssetManager} the contains the desired sound.
     * @param paths     a {@link Collection} of sound identifiers.
     * @return          a new {@code Sound} instance.
     *
     * @throws NullPointerException if the given {@code AssetManager} or path
     *                              {@code Collection} are {@code null}.
     * @throws GdxRuntimeException  if any of the assets identified by the
     *                              given paths aren't managed by the
     *                              {@code AssetManager} or haven't been
     *                              loaded.
     * @see RandomSound
     */
    public static Sound newSound(AssetManager assets, Collection<String> paths) {
        Objects.requireNonNull(assets);
        Objects.requireNonNull(paths);

        if (paths.size() == 1) {
            String path = paths.iterator().next();
            return newSound(assets, path);
        }
        Collection<Sound> effects = new ArrayList<>(paths.size());
        for (String path : paths) {
            effects.add(newSound(assets, path));
        }
        return new RandomSound(effects);
    }
}
