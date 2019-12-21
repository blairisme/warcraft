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
 * A spell that creates a magical explosive trap that explodes when any type of
 * game object approaches it. The trap will eventually exploded it unused.
 *
 * @author Blair Butterworth
 */
public class RunesSpell extends SpellSequence
{
    @Inject
    public RunesSpell(AoeSpellAction spell, MoveWithCastingRangeAction move) {
        super(spell, move);
        move.setSpell(Spell.Runes);
        spell.setSpell(Spell.Runes);
        spell.setEffect(EffectType.Spell);
        spell.setProduct(UnitType.RuneTrap);
    }
}
