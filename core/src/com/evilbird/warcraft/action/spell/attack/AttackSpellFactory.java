/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
