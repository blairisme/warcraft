/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.transport;

import com.evilbird.engine.action.ActionException;

/**
 * Thrown when transportation fails.
 *
 * @author Blair Butterworth
 */
public class TransportFailed extends ActionException
{
    public TransportFailed(String message) {
        super(message);
    }
}
