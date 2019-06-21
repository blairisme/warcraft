/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.state;

/**
 * Thrown when an unexpected error occurs read or writing from
 * {@link State game state} persistence.
 *
 * @author Blair Butterworth
 */
public class StateLoadError extends RuntimeException
{
    public StateLoadError(Throwable cause) {
        super(cause);
    }
}
