/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.spell.SpellProvider;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.spell.SpellActions.EyeOfKilroggSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.PolymorphSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.PolymorphSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.RaiseDeadSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.RunesSpell;

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
        InjectedPool<PolymorphSpell> polymorphSpell,
        InjectedPool<PolymorphSelect> polymorphSelect,
        InjectedPool<RaiseDeadSpell> raiseDeadSpell,
        InjectedPool<RunesSpell> runesSpell)
    {
        addActionPool(EyeOfKilroggSpell, eyeOfKilroggSpell);
        addActionPool(PolymorphSpell, polymorphSpell);
        addActionPool(PolymorphSelect, polymorphSelect);
        addActionPool(RaiseDeadSpell, raiseDeadSpell);
        addActionPool(RunesSpell, runesSpell);
    }
}
