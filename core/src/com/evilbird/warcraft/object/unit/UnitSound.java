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
 * Defines options of specifying unit sound varieties.
 *
 * @author Blair Butterworth
 */
public enum UnitSound implements Identifier
{
    Attack,
    Hit,
    Die,

    Selected,
    Acknowledge,

    Captured,
    Rescued,

    Placement,
    Build,
    Complete,
    Ready,

    ChopWood,

    DepositGold,
    DepositOil,
    DepositWood
}
