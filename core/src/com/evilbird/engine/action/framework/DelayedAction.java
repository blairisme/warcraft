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
import com.evilbird.engine.common.function.Supplier;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this class provide an {@link Action Action} that is only
 * acted upon when indicated to do so by a given {@link Supplier validator}.
 *
 * @author Blair Butterworth
 */
public class DelayedAction extends BasicAction
{
    private float duration;
    private float total;

    public DelayedAction() {
    }

    public DelayedAction(float seconds) {
        setDuration(seconds);
    }

    public void setDuration(float seconds) {
        this.duration = seconds;
        this.total = 0;
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
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

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
