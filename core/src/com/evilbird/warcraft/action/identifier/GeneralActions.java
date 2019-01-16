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

/**
 * Defines options of specifying general/common action varieties.
 *
 * @author Blair Butterworth
 */
public enum GeneralActions implements ActionIdentifier
{
    Attack,
    Reposition,
    Move,
    Select,
}