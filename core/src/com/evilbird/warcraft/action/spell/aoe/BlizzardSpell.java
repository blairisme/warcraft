/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
