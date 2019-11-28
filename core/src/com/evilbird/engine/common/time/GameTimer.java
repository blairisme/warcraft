/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.time;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A simple timer that counts the time elapsed by successive game loop updates.
 *
 * @author Blair Butterworth
 */
public class GameTimer
{
    private float duration;
    private float progress;

    public GameTimer() {
        this(0, 0);
    }

    public GameTimer(float duration) {
        this(duration, 0);
    }

    public GameTimer(float duration, float progress) {
        this.duration = duration;
        this.progress = progress;
    }

    public boolean advance(float time) {
        progress += time;
        return complete();
    }

    public boolean complete() {
        return progress >= duration;
    }

    public float completion() {
        return progress / duration;
    }

    public float duration() {
        return duration;
    }

    public void end() {
        progress = Float.MAX_VALUE;
    }

    public void reset() {
        progress = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        GameTimer gameTimer = (GameTimer)obj;
        return new EqualsBuilder()
            .append(duration, gameTimer.duration)
            .append(progress, gameTimer.progress)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(duration)
            .append(progress)
            .toHashCode();
    }
}
