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
 * A spell that conjures a blizzard, dealing damage to all game objects in a
 * given area for a period of time.
 *
 * @author Blair Butterworth
 */
public class BlizzardSpell extends SpellSequence
{
    @Inject
    public BlizzardSpell(AoeSpellAction cast, MoveWithinCastingRangeAction move) {
        super(cast, move);
        move.setSpell(Spell.Blizzard);
        cast.setSpell(Spell.Blizzard);
        cast.setEffect(EffectType.Spell);
        cast.setProduct(UnitType.Blizzard);
    }
}
