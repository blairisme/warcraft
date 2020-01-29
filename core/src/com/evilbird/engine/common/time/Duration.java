/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.time;

import static com.evilbird.engine.common.time.ChronoUnit.NANOS;
import static com.evilbird.engine.common.time.ChronoUnit.SECONDS;

/**
 * A time-based amount of time, such as '34.5 seconds'.
 */
public class Duration
{
    /**
     * Constant for a duration of zero.
     */
    public static final Duration ZERO = new Duration(0, 0);

    /**
     * The number of seconds in the duration.
     */
    private final long seconds;

    /**
     * The number of nanoseconds in the duration, expressed as a fraction of the
     * number of seconds. This is always positive, and never exceeds 999,999,999.
     */
    private final int nanos;

    /**
     * Obtains a {@code Duration} representing a number of seconds.
     *
     * @param seconds   the number of seconds, positive or negative
     * @return          a {@code Duration}, not null
     */
    public static Duration ofSeconds(long seconds) {
        return create(seconds, 0);
    }

    /**
     * Obtains an instance of {@code Duration} using seconds and nanoseconds.
     *
     * @param seconds           the length of the duration in seconds, positive
     *                          or negative
     * @param nanoAdjustment    the nanosecond adjustment within the second,
     *                          from 0 to 999,999,999
     */
    private static Duration create(long seconds, int nanoAdjustment) {
        if ((seconds | nanoAdjustment) == 0) {
            return ZERO;
        }
        return new Duration(seconds, nanoAdjustment);
    }

    /**
     * Constructs an instance of {@code Duration} using seconds and
     * nanoseconds.
     *
     * @param seconds  the length of the duration in seconds, positive or
     *                 negative
     * @param nanos     the nanoseconds within the second, from 0 to
     *                  999,999,999.
     */
    private Duration(long seconds, int nanos) {
        super();
        this.seconds = seconds;
        this.nanos = nanos;
    }

    /**
     * Gets the value of the requested unit, either in
     * {@link ChronoUnit#SECONDS seconds} or {@link ChronoUnit#NANOS nanos}.
     * All other units throw an exception.
     *
     * @param unit  the {@code TemporalUnit} for which to return the value
     * @return      the long value of the unit
     */
    public long get(TemporalUnit unit) {
        if (unit == SECONDS) {
            return seconds;
        } else if (unit == NANOS) {
            return nanos;
        } else {
            throw new UnsupportedOperationException("Unsupported unit: " + unit);
        }
    }
}
