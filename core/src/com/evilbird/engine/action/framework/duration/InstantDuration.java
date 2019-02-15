/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework.duration;

public class InstantDuration implements ActionDuration
{
    @Override
    public boolean isComplete(float time) {
        return true;
    }

    @Override
    public void restart() {
    }
}
