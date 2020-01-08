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
 * A spell that conjures death and decay, dealing damage to all game objects in
 * a given area for a period of time.
 *
 * @author Blair Butterworth
 */
public class DeathAndDecaySpell extends SpellSequence
{
    @Inject
    public DeathAndDecaySpell(AoeSpellAction spell, MoveWithinCastingRangeAction move) {
        super(spell, move);
        move.setSpell(Spell.DeathAndDecay);
        spell.setSpell(Spell.DeathAndDecay);
        spell.setEffect(EffectType.Spell);
        spell.setProduct(UnitType.DeathAndDecay);
    }
}
