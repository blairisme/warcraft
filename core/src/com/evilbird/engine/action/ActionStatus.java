/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action;

/**
 * Defines possible action states.
 *
 * @author Blair Butterworth
 */
public enum ActionStatus
{
    /** Indicates that the Action has never been run or has been reset. */
    Fresh,

    /** Indicates that the Action is currently running. */
    Running,

    /** Indicates that the Action returned a failure result. */
    Failed,

    /** Indicates that the Action returned a success result. */
    Succeeded,

    /** Indicates that the Action has been terminated. */
    Cancelled;
}
