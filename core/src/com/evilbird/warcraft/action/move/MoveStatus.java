/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
