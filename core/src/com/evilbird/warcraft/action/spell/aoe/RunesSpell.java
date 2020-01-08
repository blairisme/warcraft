/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.warcraft.action.move.MoveWithinCastingRangeAction;
import com.evilbird.warcraft.action.spell.SpellSequence;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

/**
 * A spell that creates a magical explosive trap that explodes when any type of
 * game object approaches it. The trap will eventually exploded it unused.
 *
 * @author Blair Butterworth
 */
public class RunesSpell extends SpellSequence
{
    @Inject
    public RunesSpell(AoeSpellAction spell, MoveWithinCastingRangeAction move) {
        super(spell, move);
        move.setSpell(Spell.Runes);
        spell.setSpell(Spell.Runes);
        spell.setEffect(EffectType.Spell);
        spell.setProduct(UnitType.RuneTrap);
    }
}
