/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.effect.environmental;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.item.effect.Effect;
import com.evilbird.warcraft.item.effect.EffectType;

import javax.inject.Inject;

/**
 * A factory for the creation of environmental effects.
 *
 * @author Blair Butterworth
 */
public class EnvironmentalEffects extends GameFactorySet<Effect>
{
    @Inject
    public EnvironmentalEffects(
        BlizzardFactory blizzardFactory,
        FireFactory fireFactory,
        FlameFactory flameFactory,
        RuneFactory runeFactory,
        TornadoFactory tornadoFactory)
    {
        addProvider(EffectType.Blizzard, blizzardFactory);
        addProvider(EffectType.Fire, fireFactory);
        addProvider(EffectType.Flame, flameFactory);
        addProvider(EffectType.Rune, runeFactory);
        addProvider(EffectType.Tornado, tornadoFactory);
    }
}
