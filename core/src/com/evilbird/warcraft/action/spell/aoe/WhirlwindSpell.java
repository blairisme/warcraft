/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.warcraft.action.move.MoveWithCastingRangeAction;
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
    public WhirlwindSpell(AoeSpellAction spell, MoveWithCastingRangeAction move) {
        super(spell, move);
        move.setSpell(Spell.Whirlwind);
        spell.setSpell(Spell.Whirlwind);
        spell.setEffect(EffectType.Spell);
        spell.setProduct(UnitType.Whirlwind);
    }
}
