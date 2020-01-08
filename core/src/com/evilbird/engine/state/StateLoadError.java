/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
