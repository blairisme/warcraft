/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.error;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Thrown if a requested entity doesn't exist.
 *
 * @author Blair Butterworth
 */
public class UnknownEntityException extends RuntimeException
{
    public UnknownEntityException(Identifier identifier) {
        super("Entity doesn't exist: " + identifier);
    }
}
