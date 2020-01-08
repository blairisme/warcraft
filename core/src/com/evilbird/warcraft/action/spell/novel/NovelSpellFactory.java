/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
