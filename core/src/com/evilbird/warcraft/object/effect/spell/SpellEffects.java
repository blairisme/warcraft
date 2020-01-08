/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.effect.spell;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.effect.Effect;
import com.evilbird.warcraft.object.effect.EffectType;

import javax.inject.Inject;

/**
 * A factory for the creation of spell effects.
 *
 * @author Blair Butterworth
 */
public class SpellEffects extends GameFactorySet<Effect>
{
    @Inject
    public SpellEffects(
        ExorcismFactory exorcismFactory,
        GeneralSpellFactory generalSpellFactory,
        HealFactory healFactory)
    {
        addProvider(EffectType.Exorcism, exorcismFactory);
        addProvider(EffectType.Spell, generalSpellFactory);
        addProvider(EffectType.Heal, healFactory);
    }
}
