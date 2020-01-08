/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.effect.environmental;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.effect.Effect;
import com.evilbird.warcraft.object.effect.EffectType;

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
        FireFactory fireFactory,
        FlameFactory flameFactory,
        RuneFactory runeFactory)
    {
        addProvider(EffectType.Fire, fireFactory);
        addProvider(EffectType.Flame, flameFactory);
        addProvider(EffectType.Rune, runeFactory);
    }
}
