/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.conjured;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.conjured.blizzard.BlizzardFactory;
import com.evilbird.warcraft.object.unit.conjured.decay.DeathAndDecayFactory;
import com.evilbird.warcraft.object.unit.conjured.flameshield.FlameShieldFactory;
import com.evilbird.warcraft.object.unit.conjured.rune.RuneTrapFactory;
import com.evilbird.warcraft.object.unit.conjured.whirlwind.WhirlwindFactory;

import javax.inject.Inject;

/**
 * A factory that creates {@link ConjuredObject ConjuredObjects}.
 *
 * @author Blair Butterworth
 */
public class ConjuredFactory extends GameFactorySet<ConjuredObject>
{
    @Inject
    public ConjuredFactory(
        BlizzardFactory blizzardFactory,
        DeathAndDecayFactory deathAndDecayFactory,
        FlameShieldFactory flameShieldFactory,
        RuneTrapFactory runeTrapFactory,
        WhirlwindFactory whirlwindFactory)
    {
        addProvider(UnitType.Blizzard, blizzardFactory);
        addProvider(UnitType.DeathAndDecay, deathAndDecayFactory);
        addProvider(UnitType.FlameShield, flameShieldFactory);
        addProvider(UnitType.RuneTrap, runeTrapFactory);
        addProvider(UnitType.Whirlwind, whirlwindFactory);
    }
}
