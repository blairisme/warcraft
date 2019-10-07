/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.effect.spell;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.item.effect.Effect;
import com.evilbird.warcraft.item.effect.EffectType;

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
        DeathAndDecayFactory deathAndDecayFactory,
        ExorcismFactory exorcismFactory,
        FlameShieldFactory flameShieldFactory,
        GeneralSpellFactory generalSpellFactory,
        HealFactory healFactory)
    {
        addProvider(EffectType.DeathAndDecay, deathAndDecayFactory);
        addProvider(EffectType.Exorcism, exorcismFactory);
        addProvider(EffectType.FlameShield, flameShieldFactory);
        addProvider(EffectType.Spell, generalSpellFactory);
        addProvider(EffectType.Heal, healFactory);
    }
}
