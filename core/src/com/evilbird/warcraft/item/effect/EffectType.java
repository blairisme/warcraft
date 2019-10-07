/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.effect;

import com.evilbird.engine.item.ItemType;

/**
 * Defines options of specifying effect varieties.
 *
 * @author Blair Butterworth
 */
public enum EffectType implements ItemType
{
    /* Confirmation */
    Attack,
    Confirm,

    /* Environmental */
    Blizzard,
    Fire,
    Flame,
    Rune,
    Tornado,

    /* Explosion */
    BallistaExplosion,
    CannonExplosion,
    TowerExplosion,
    Explosion,

    /* Spell */
    DeathAndDecay,
    Exorcism,
    FlameShield,
    Heal,
    Spell
}
