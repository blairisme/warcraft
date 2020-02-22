/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.time.ChronoUnit;
import com.evilbird.engine.common.time.Duration;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this class provide an {@link Action Action} that is only
 * acted upon when a given time has elapsed.
 *
 * @author Blair Butterworth
 */
public class TemporalAction extends AbstractAction
{
    private float duration;
    private float total;

    protected TemporalAction() {
    }

    /**
     * Constructs a new instance of this class lasting the given time.
     *
     * @param duration  the length of the delay, in seconds.
     */
    public TemporalAction(float duration) {
        this.total = 0;
        this.duration = duration;
    }

    /**
     * Constructs a new instance of this class starting and lasting the given
     * times.
     *
     * @param start     that starting point of the delay, if the delay is
     *                  partially complete.
     * @param duration  the length of the delay, in seconds.
     */
    public TemporalAction(float start, float duration) {
        this.total = start;
        this.duration = duration;
    }

    protected float getDuration() {
        return duration;
    }

    protected void setDuration(Duration duration) {
        setDuration(duration.get(ChronoUnit.SECONDS));
    }

    protected void setDuration(float duration) {
        this.duration = duration;
    }

    protected void setProgress(float progress) {
        this.total = progress;
    }

    @Override
    public boolean act(float delta) {
        total += delta;
        return isComplete();
    }

    public boolean isComplete() {
        return total >= duration;
    }

    public float getProgress() {
        if (total == 0) {
            return 0;
        }
        if (total >= duration) {
            return 1;
        }
        return total / duration;
    }

    @Override
    public void restart() {
        total = 0;
    }

    @Override
    public void reset() {
        total = 0;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("base")
            .append("duration", duration)
            .append("total", total)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        TemporalAction that = (TemporalAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(duration, that.duration)
            .append(total, that.total)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(duration)
            .append(total)
            .toHashCode();
    }
}
