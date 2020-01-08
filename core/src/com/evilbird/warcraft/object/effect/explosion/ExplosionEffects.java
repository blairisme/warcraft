/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.effect.explosion;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.effect.Effect;
import com.evilbird.warcraft.object.effect.EffectType;

import javax.inject.Inject;

/**
 * A factory for the creation of explosion effects.
 *
 * @author Blair Butterworth
 */
public class ExplosionEffects extends GameFactorySet<Effect>
{
    @Inject
    public ExplosionEffects(
        BallistaExplosionFactory ballistaExplosionFactory,
        CannonExplosionFactory cannonExplosionFactory,
        GeneralExplosionFactory generalExplosionFactory,
        LightningExplosionFactory lightningExplosionFactory,
        TouchOfDeathExplosionFactory touchOfDeathExplosionFactory,
        TowerExplosionFactory towerExplosionFactory)
    {
        addProvider(EffectType.Explosion, generalExplosionFactory);
        addProvider(EffectType.SiegeExplosion, ballistaExplosionFactory);
        addProvider(EffectType.CannonExplosion, cannonExplosionFactory);
        addProvider(EffectType.LightningExplosion, lightningExplosionFactory);
        addProvider(EffectType.TouchOfDeathExplosion, touchOfDeathExplosionFactory);
        addProvider(EffectType.TowerExplosion, towerExplosionFactory);
    }
}
