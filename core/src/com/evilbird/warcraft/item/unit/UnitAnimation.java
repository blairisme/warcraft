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
