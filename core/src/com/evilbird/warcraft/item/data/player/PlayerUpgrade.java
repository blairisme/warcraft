/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Defines options for the various statistics kept about player actions.
 *
 * @author Blair Butterworth
 */
public enum PlayerUpgrade implements Identifier
{
    ArmourPlating1,
    ArmourPlating2,

    CannonDamage1,
    CannonDamage2,

    MeleeDamage1,
    MeleeDamage2,

    MeleeDefence1,
    MeleeDefence2,

    MountedSpeed1,
    MountedSpeed2,

    RangedDamage1,
    RangedDamage2,

    RangedAccuracy1,
    RangedAccuracy2,

    SiegeDamage,

    GoldProduction,
    OilProduction,
    WoodProduction,
}
