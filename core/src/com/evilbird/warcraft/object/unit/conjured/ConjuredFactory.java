/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.conjured.blizzard.BlizzardFactory;
import com.evilbird.warcraft.object.unit.conjured.decay.DeathAndDecayFactory;
import com.evilbird.warcraft.object.unit.conjured.fireball.FireballFactory;
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
        FireballFactory fireballFactory,
        FlameShieldFactory flameShieldFactory,
        RuneTrapFactory runeTrapFactory,
        WhirlwindFactory whirlwindFactory)
    {
        addProvider(UnitType.Blizzard, blizzardFactory);
        addProvider(UnitType.DeathAndDecay, deathAndDecayFactory);
        addProvider(UnitType.Fireball, fireballFactory);
        addProvider(UnitType.FlameShield, flameShieldFactory);
        addProvider(UnitType.RuneTrap, runeTrapFactory);
        addProvider(UnitType.Whirlwind, whirlwindFactory);
    }
}
