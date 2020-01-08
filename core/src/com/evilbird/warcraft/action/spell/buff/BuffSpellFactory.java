/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.spell.SpellProvider;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.spell.SpellActions.BloodlustSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.BloodlustSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.DeathCoilSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.DeathCoilSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.HasteSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.HasteSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.HealSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.HealSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.InvisibilitySelect;
import static com.evilbird.warcraft.action.spell.SpellActions.InvisibilitySpell;
import static com.evilbird.warcraft.action.spell.SpellActions.SlowSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.SlowSpell;

/**
 * A factory that creates buff spell actions.
 *
 * @author Blair Butterworth
 */
public class BuffSpellFactory extends SpellProvider
{
    @Inject
    public BuffSpellFactory(
        InjectedPool<BloodlustSequence> bloodlustSpell,
        InjectedPool<BloodlustSelect> bloodlustSelect,
        InjectedPool<DeathCoilSequence> deathCoilSpell,
        InjectedPool<DeathCoilSelect> deathCoilSelect,
        InjectedPool<HasteSequence> hasteSpell,
        InjectedPool<HasteSelect> hasteSelect,
        InjectedPool<HealSequence> healSpell,
        InjectedPool<HealSelect> healSelect,
        InjectedPool<InvisibilitySequence> invisibilitySpell,
        InjectedPool<InvisibilitySelect> invisibilitySelect,
        InjectedPool<SlowSequence> slowSpell,
        InjectedPool<SlowSelect> slowSelect)
    {
        addActionPool(BloodlustSpell, bloodlustSpell);
        addActionPool(BloodlustSelect, bloodlustSelect);
        addActionPool(DeathCoilSpell, deathCoilSpell);
        addActionPool(DeathCoilSelect, deathCoilSelect);
        addActionPool(HasteSpell, hasteSpell);
        addActionPool(HasteSelect, hasteSelect);
        addActionPool(HealSpell, healSpell);
        addActionPool(HealSelect, healSelect);
        addActionPool(InvisibilitySpell, invisibilitySpell);
        addActionPool(InvisibilitySelect, invisibilitySelect);
        addActionPool(SlowSpell, slowSpell);
        addActionPool(SlowSelect, slowSelect);
    }
}
