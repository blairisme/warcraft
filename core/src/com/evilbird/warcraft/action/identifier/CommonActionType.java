/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.identifier;

import com.evilbird.engine.action.ActionIdentifier;

public enum CommonActionType implements ActionIdentifier
{
    Select,
    Move,
    Attack,
    Stop,
    Cancel,
    Repair,
    Reposition
}
