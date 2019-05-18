/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this class provide an {@link Action Action} that is only
 * acted upon when a given time has elapsed.
 *
 * @author Blair Butterworth
 */
public class DelayedAction extends BasicAction
{
    private float duration;
    private float total;

    private DelayedAction() {
    }

    /**
     * Constructs a new instance of this class lasting the given time.
     *
     * @param duration  the length of the delay, in seconds.
     */
    public DelayedAction(float duration) {
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
    public DelayedAction(float start, float duration) {
        this.total = start;
        this.duration = duration;
    }

    /**
     * Returns a delay action lasting the given time.
     *
     * @param duration  the length of the delay, in seconds.
     *
     * @return a new DelayedAction.
     */
    public static DelayedAction delay(float duration) {
        return new DelayedAction(duration);
    }

    /**
     * Returns a delay action starting and lasting the given times.
     *
     * @param start     that starting point of the delay, if the delay is
     *                  partially complete.
     * @param duration  the length of the delay, in seconds.
     *
     * @return a new DelayedAction.
     */
    public static DelayedAction delay(float start, float duration) {
        return new DelayedAction(start, duration);
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

        DelayedAction that = (DelayedAction)obj;
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
