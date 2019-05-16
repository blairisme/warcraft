/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.ActionIdentifier;

/**
 * Defines options for specifying attack action varieties.
 *
 * @author Blair Butterworth
 */
public enum AttackActions implements ActionIdentifier
{
    AttackMelee,
    AttackCancel,
}
