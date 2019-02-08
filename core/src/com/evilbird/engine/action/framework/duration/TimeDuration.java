/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework.duration;

public class TimeDuration implements ActionDuration
{
    private float duration;
    private float total;

    /**
     * Creates a new instance of this class given the length of the duration.
     *
     * @param duration the length of the duration specified in seconds.
     */
    public TimeDuration(float duration) {
        this.duration = duration;
        restart();
    }

    @Override
    public boolean isComplete(float time) {
        total += time;
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
        this.total = 0;
    }
}
