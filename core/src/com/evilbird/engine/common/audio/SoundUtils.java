/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.evilbird.engine.common.file.FileType;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class provide common utility functions for working with
 * sound effects.
 *
 * @author Blair Butterworth
 */
public class SoundUtils
{
    private SoundUtils() {
    }

    public static SoundEffect newSoundEffect(AssetManager assets, String path) {
        Sound sound = assets.get(path, Sound.class);
        return new BasicSoundEffect(sound, path);
    }

    public static SoundEffect newSoundEffect(AssetManager assets, String... paths) {
        Collection<SoundEffect> effects = new ArrayList<>(paths.length);
        for (String path : paths) {
            effects.add(newSoundEffect(assets, path));
        }
        return new SoundEffectSet(effects);
    }

    public static SoundEffect newSoundEffect(AssetManager assets, Collection<String> paths) {
        Collection<SoundEffect> effects = new ArrayList<>(paths.size());
        for (String path : paths) {
            effects.add(newSoundEffect(assets, path));
        }
        return new SoundEffectSet(effects);
    }

    public static SoundEffect newSoundEffect(AssetManager assets, String prefix, FileType type, int count) {
        return newSoundEffect(assets, prefix, type.getFileExtension(), 1, count);
    }

    public static SoundEffect newSoundEffect(AssetManager assets, String prefix, String suffix, int start, int end) {
        Collection<String> paths = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            paths.add(prefix + i + suffix);
        }
        return newSoundEffect(assets, paths);
    }
}
