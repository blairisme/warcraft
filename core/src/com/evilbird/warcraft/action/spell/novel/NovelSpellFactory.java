/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.novel;

import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.spell.SpellDeselect;
import com.evilbird.warcraft.action.spell.SpellProvider;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.spell.SpellActions.HolyVisionSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.SpellDeselect;

/**
 * A factory that creates novel spell actions.
 *
 * @author Blair Butterworth
 */
public class NovelSpellFactory extends SpellProvider
{
    @Inject
    public NovelSpellFactory(
        InjectedPool<HolyVisionSpell> holyVisionSpell,
        InjectedPool<SpellDeselect> spellDeselect)
    {
        addActionPool(HolyVisionSpell, holyVisionSpell);
        addActionPool(SpellDeselect, spellDeselect);
    }
}
