/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.move;

/**
 * Specifies the set of options describing states in the move sequence.
 *
 * @author Blair Butterworth
 */
public enum MoveStatus
{
    Complete,
    Cancelled,
    Failed,
    Updated
}
