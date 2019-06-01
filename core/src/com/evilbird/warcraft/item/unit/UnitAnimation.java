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
 * Defines options of specifying unit animation varieties.
 *
 * @author Blair Butterworth
 */
@SerializedType("UnitAnimation")
public enum UnitAnimation implements Identifier
{
    Hidden,
    Idle,
    IdleBasic,
    IdleGold,
    IdleWood,

    Move,
    MoveBasic,
    MoveGold,
    MoveWood,

    MeleeAttack,
    Build,
    Construct,
    BuildingSite,

    Death,
    Dead,
    Decompose,

    Gathering,
    GatherGold,
    GatherWood
}
