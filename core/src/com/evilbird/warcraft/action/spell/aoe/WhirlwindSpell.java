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
 * A spell that conjures a whirlwind, dealing damage to all game objects in a
 * given area as well as stopping those objects from receiving commands.
 *
 * @author Blair Butterworth
 */
public class WhirlwindSpell extends SpellSequence
{
    @Inject
    public WhirlwindSpell(AoeSpellAction spell, MoveWithinCastingRangeAction move) {
        super(spell, move);
        move.setSpell(Spell.Whirlwind);
        spell.setSpell(Spell.Whirlwind);
        spell.setEffect(EffectType.Spell);
        spell.setProduct(UnitType.Whirlwind);
    }
}
