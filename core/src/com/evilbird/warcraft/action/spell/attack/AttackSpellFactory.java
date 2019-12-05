/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.spell.SpellProvider;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.spell.SpellActions.ExorcismSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.ExorcismSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.FlameShieldSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.FlameShieldSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.UnholyArmourSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.UnholyArmourSpell;

/**
 * A factory that creates attack spell actions.
 *
 * @author Blair Butterworth
 */
public class AttackSpellFactory extends SpellProvider
{
    @Inject
    public AttackSpellFactory(
        InjectedPool<ExorcismSpell> exorcismSpell,
        InjectedPool<ExorcismSelect> exorcismSelect,
        InjectedPool<FlameShieldSequence> flameShieldSpell,
        InjectedPool<FlameShieldSelect> flameShieldSelect,
        InjectedPool<UnholyArmourSequence> unholyArmourSpell,
        InjectedPool<UnholyArmourSelect> unholyArmourSelect)
    {
        addActionPool(ExorcismSpell, exorcismSpell);
        addActionPool(ExorcismSelect, exorcismSelect);
        addActionPool(FlameShieldSpell, flameShieldSpell);
        addActionPool(FlameShieldSelect, flameShieldSelect);
        addActionPool(UnholyArmourSpell, unholyArmourSpell);
        addActionPool(UnholyArmourSelect, unholyArmourSelect);
    }
}
