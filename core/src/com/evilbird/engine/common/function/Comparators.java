/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.function;

import java.util.Comparator;

/**
 * Contains common {@link Comparator Comparators}.
 *
 * @author Blair Butterworth
 */
public class Comparators
{
    /**
     * Disable construction of static helper class.
     */
    private Comparators() {
    }

    public static <T> Comparator<T> equality() {
        return (a, b) -> 0;
    }
}
