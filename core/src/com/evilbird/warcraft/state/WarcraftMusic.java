/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

import static com.evilbird.engine.common.file.FileType.MP3;

/**
 * Provides background music for the Warcraft game.
 *
 * @author Blair Butterworth
 */
public class WarcraftMusic extends AssetBundle
{
    private static final int MUSIC_COUNT = 15;
    private static final String MUSIC_ID = "music";
    private static final String MUSIC_DIRECTORY = "data/music/";

    @Inject
    public WarcraftMusic(Device device) {
        this(device.getAssetStorage());
    }

    public WarcraftMusic(AssetManager assetManager) {
        super(assetManager);
        registerSequence(MUSIC_ID, MUSIC_DIRECTORY, MP3.getFileExtension(), MUSIC_COUNT, Music.class);
    }

    public Music getMusic() {
        return getLazyLoadedMusicSequence(MUSIC_ID, MUSIC_COUNT);
    }
}
