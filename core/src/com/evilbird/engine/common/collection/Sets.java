/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Instances of this class provide helper functions for working with
 * {@link Set Sets}.
 *
 * @author Blair Butterworth
 */
public class Sets
{
    private Sets() {
    }

    public static <T> Set<T> of(T ... elements) {
        return Arrays.stream(elements).collect(Collectors.toCollection(HashSet::new));
    }
}
