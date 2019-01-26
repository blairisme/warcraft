/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai;

import com.evilbird.engine.item.ItemRoot;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

public abstract class PeriodicProcedure implements AiProcedure
{
    private long period;
    private TimeUnit periodUnit;
    private StopWatch stopwatch;

    public PeriodicProcedure(long period, TimeUnit periodUnit) {
        this.period = period;
        this.periodUnit = periodUnit;
        this.stopwatch = StopWatch.createStarted();
    }

    @Override
    public void update(ItemRoot gameState) {
        if (stopwatch.getTime(periodUnit) >= period) {
            periodicUpdate(gameState);
            stopwatch.reset();
            stopwatch.start();
        }
    }

    protected abstract void periodicUpdate(ItemRoot gameState);
}
