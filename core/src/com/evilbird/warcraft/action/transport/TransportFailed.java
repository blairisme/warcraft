/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
