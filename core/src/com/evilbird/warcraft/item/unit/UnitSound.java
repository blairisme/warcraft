/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializedType;

/**
 * Defines options of specifying unit sound varieties.
 *
 * @author Blair Butterworth
 */
@SerializedType("UnitSound")
public enum UnitSound implements Identifier
{
    Attack,
    Hit,
    Die,

    Selected,
    Acknowledge,
    Captured,

    Build,
    Complete,
    Ready,

    MineGold,
    GatherOil,
    ChopWood,

    DepositGold,
    DepositOil,
    DepositWood
}
