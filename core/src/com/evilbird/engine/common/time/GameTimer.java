/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.time;

/**
 * A simple timer that counts the time elapsed by successive game loop updates.
 *
 * @author Blair Butterworth
 */
public class GameTimer
{
    private float duration;
    private float progress;

    public GameTimer(float duration) {
        this.duration = duration;
        this.progress = 0;
    }

    public boolean advance(float time) {
        progress += time;
        return complete();
    }

    public boolean complete() {
        return progress >= duration;
    }

    public void reset() {
        progress = 0;
    }
}
