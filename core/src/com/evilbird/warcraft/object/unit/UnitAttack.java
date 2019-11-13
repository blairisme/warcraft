/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Defines identifiers for unit attack varieties.
 *
 * @author Blair Butterworth
 */
public enum UnitAttack implements Identifier
{
    None,
    Melee,
    Ranged,
    Ship,
    Siege,
    Magic,
}
