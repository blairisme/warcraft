/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
    LightDamage,
    HeavyDamage,

    GatherWood,
    ExtractGold,
    ExtractOil
}
