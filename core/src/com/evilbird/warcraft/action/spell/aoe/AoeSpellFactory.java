/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.spell.SpellProvider;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.spell.SpellActions.BlizzardSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.DeathAndDecaySpell;
import static com.evilbird.warcraft.action.spell.SpellActions.RunesSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.WhirlwindSpell;

/**
 * A factory that creates area of effect spell actions.
 *
 * @author Blair Butterworth
 */
public class AoeSpellFactory extends SpellProvider
{
    @Inject
    public AoeSpellFactory(
        InjectedPool<BlizzardSpell> blizzardSpell,
        InjectedPool<DeathAndDecaySpell> deathAndDecayPool,
        InjectedPool<WhirlwindSpell> whirlwindPool,
        InjectedPool<RunesSpell> runePool)
    {
        addActionPool(BlizzardSpell, blizzardSpell);
        addActionPool(DeathAndDecaySpell, deathAndDecayPool);
        addActionPool(WhirlwindSpell, whirlwindPool);
        addActionPool(RunesSpell, runePool);
    }
}
