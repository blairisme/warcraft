/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft;

import android.media.MediaMetadataRetriever;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.evilbird.engine.audio.sound.AbstractSound;
import com.evilbird.engine.audio.sound.Sound;

import java.io.File;

import static android.media.MediaMetadataRetriever.METADATA_KEY_DURATION;

/**
 * A {@link Sound} implementation for use on Android devices.
 *
 * @author Blair Butterworth
 */
public class AndroidSound extends AbstractSound
{
    private static final transient long INVALID = -1;

    private long start;
    private long duration;

    public AndroidSound(FileHandleResolver resolver, String path) {
        super(resolver, path);
        this.duration = calculateDuration(resolver, path);
    }

    @Override
    public boolean isPlaying() {
        long current = System.currentTimeMillis();
        return start != INVALID && current - start > duration;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public void play() {
        super.play();
        start = System.currentTimeMillis();
    }

    @Override
    public void stop() {
        super.stop();
        start = INVALID;
    }

    private long calculateDuration(FileHandleResolver resolver, String path) {
        FileHandle handle = resolver.resolve(path);
        File file = handle.file();
        MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
        metadataRetriever.setDataSource(file.getAbsolutePath());
        String duration = metadataRetriever.extractMetadata(METADATA_KEY_DURATION);
        return Long.parseLong(duration);
    }
}
