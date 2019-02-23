/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.item.animated.SoundIdentifier;

public enum UnitSound implements SoundIdentifier
{
    Attack,
    Die,

    Selected,
    Acknowledge,

    Construct,
    Complete,
    Ready,

    GatherGold,
    GatherOil,
    GatherWood,

    DepositGold,
    DepositOil,
    DepositWood;
}
