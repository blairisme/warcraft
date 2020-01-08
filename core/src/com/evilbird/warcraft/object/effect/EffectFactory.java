/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.effect;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.effect.confirmation.ConfirmationEffects;
import com.evilbird.warcraft.object.effect.environmental.EnvironmentalEffects;
import com.evilbird.warcraft.object.effect.explosion.ExplosionEffects;
import com.evilbird.warcraft.object.effect.spell.SpellEffects;

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
