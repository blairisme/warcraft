/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.spell.SpellProvider;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.spell.SpellActions.BlizzardSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.BlizzardSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.DeathAndDecaySpell;
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
        InjectedPool<AoeSpellSelect> blizzardSelect,
        InjectedPool<DeathAndDecaySpell> deathAndDecayPool,
        InjectedPool<WhirlwindSpell> whirlwindPool)
    {
        addActionPool(BlizzardSpell, blizzardSpell);
        addActionPool(BlizzardSelect, blizzardSelect);
        addActionPool(DeathAndDecaySpell, deathAndDecayPool);
        addActionPool(WhirlwindSpell, whirlwindPool);
    }
}
