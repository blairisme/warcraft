/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
