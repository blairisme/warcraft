/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.effect;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.effect.confirmation.ConfirmationEffects;
import com.evilbird.warcraft.item.effect.environmental.EnvironmentalEffects;
import com.evilbird.warcraft.item.effect.explosion.ExplosionEffects;
import com.evilbird.warcraft.item.effect.spell.SpellEffects;

import javax.inject.Inject;

/**
 * A factory for the creation of effect game objects: visual entities that
 * convey the consequences of an action undertaken by a game object or the
 * user.
 *
 * @author Blair Butterworth
 */
public class EffectFactory extends GameFactorySet<GameObject>
{
    @Inject
    public EffectFactory(
        ConfirmationEffects confirmationEffects,
        EnvironmentalEffects environmentalEffects,
        ExplosionEffects explosionEffects,
        SpellEffects spellEffects)
    {
        addProvider(confirmationEffects);
        addProvider(environmentalEffects);
        addProvider(explosionEffects);
        addProvider(spellEffects);
    }
}
