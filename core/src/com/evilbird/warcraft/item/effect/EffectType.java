/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.effect;

import com.evilbird.engine.object.GameObjectType;

/**
 * Defines options of specifying effect varieties.
 *
 * @author Blair Butterworth
 */
public enum EffectType implements GameObjectType
{
    /* Confirmation */
    Attack,
    Confirm,

    /* Environmental */
    Fire,
    Flame,

    /* Explosion */
    BallistaExplosion,
    CannonExplosion,
    TowerExplosion,
    Explosion,

    /* Spell */
    Exorcism,
    Heal,
    Rune,
    Spell
}
