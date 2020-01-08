/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.music;

/**
 * A {@link MusicObserver} implementation that doesn't respond to music events.
 *
 * @author Blair Butterworth
 */
public class MusicObserverAdapter implements MusicObserver
{
    @Override
    public void onStart(Music music) {
    }

    @Override
    public void onStop(Music music) {
    }

    @Override
    public void onComplete(Music music) {
    }
}
