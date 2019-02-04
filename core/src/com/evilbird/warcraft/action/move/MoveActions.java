/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.ActionIdentifier;

public enum MoveActions implements ActionIdentifier
{
    MoveToLocation,
    MoveToItem,
    MoveCancel
}
