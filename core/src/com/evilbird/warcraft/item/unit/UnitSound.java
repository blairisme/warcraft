/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializedType;

@SerializedType("UnitSound")
public enum UnitSound implements Identifier
{
    Attack,
    Die,

    Selected,
    Acknowledge,

    Construct,
    Complete,
    Ready,

    MineGold,
    GatherOil,
    ChopWood,

    DepositGold,
    DepositOil,
    DepositWood
}
