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
 * Defines options of specifying unit animation varieties.
 *
 * @author Blair Butterworth
 */
public enum UnitAnimation implements Identifier
{
    Idle,
    IdleBasic,
    IdleGold,
    IdleOil,
    IdleWood,

    Move,
    MoveBasic,
    MoveGold,
    MoveOil,
    MoveWood,

    Attack,
    CastSpell,

    Build,
    Construct,
    BuildingSite,
    BuildingUpgrade,

    Death,
    Decompose,
    LightDamage,
    HeavyDamage,

    GatherWood,
    ExtractGold,
    ExtractOil
}
