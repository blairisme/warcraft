/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.function;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Contains common {@link Consumer Consumers} and {@link BiConsumer
 * BiConsumers}.
 *
 * @author Blair Butterworth
 */
public class Consumers
{
    /**
     * Disable construction of static helper class.
     */
    private Consumers() {
    }

    public static <A, B> BiConsumer<A, B> discard() {
        return (a, b) -> {};
    }
}
