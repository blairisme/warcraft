/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.time;

/**
 * Contains common helper functions for working with Durations.
 *
 * @author Blair Butterworth
 */
public class DurationUtils
{
    private DurationUtils() {
    }

    /**
     * Obtains a {@code Duration} representing a number of seconds.
     */
    public static Duration Seconds(long seconds) {
        return Duration.ofSeconds(seconds);
    }
}
