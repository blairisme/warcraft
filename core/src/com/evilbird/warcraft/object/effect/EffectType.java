/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.effect;

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
    Explosion,
    SiegeExplosion,
    CannonExplosion,
    TowerExplosion,
    TouchOfDeathExplosion,
    LightningExplosion,

    /* Spell */
    Exorcism,
    Heal,
    Rune,
    Spell
}
