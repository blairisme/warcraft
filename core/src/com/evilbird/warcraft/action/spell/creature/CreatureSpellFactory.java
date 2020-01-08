/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.spell.SpellProvider;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.spell.SpellActions.EyeOfKilroggSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.PolymorphSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.PolymorphSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.RaiseDeadSpell;

/**
 * A factory that creates creature spell actions.
 *
 * @author Blair Butterworth
 */
public class CreatureSpellFactory extends SpellProvider
{
    @Inject
    public CreatureSpellFactory(
        InjectedPool<EyeOfKilroggSpell> eyeOfKilroggSpell,
        InjectedPool<PolymorphSequence> polymorphSpell,
        InjectedPool<PolymorphSelect> polymorphSelect,
        InjectedPool<RaiseDeadSpell> raiseDeadSpell)
    {
        addActionPool(EyeOfKilroggSpell, eyeOfKilroggSpell);
        addActionPool(PolymorphSpell, polymorphSpell);
        addActionPool(PolymorphSelect, polymorphSelect);
        addActionPool(RaiseDeadSpell, raiseDeadSpell);
    }
}
