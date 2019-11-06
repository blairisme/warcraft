/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
        InjectedPool<BloodlustSpell> bloodlustSpell,
        InjectedPool<BloodlustSpell> bloodlustSelect,
        InjectedPool<DeathCoilSpell> deathCoilSpell,
        InjectedPool<DeathCoilSelect> deathCoilSelect,
        InjectedPool<HasteSpell> hasteSpell,
        InjectedPool<HasteSelect> hasteSelect,
        InjectedPool<HealSpell> healSpell,
        InjectedPool<HealSelect> healSelect,
        InjectedPool<InvisibilitySpell> invisibilitySpell,
        InjectedPool<InvisibilitySelect> invisibilitySelect,
        InjectedPool<SlowSpell> slowSpell,
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
