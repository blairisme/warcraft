/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.warcraft.action.move.MoveWithinCastingRangeAction;
import com.evilbird.warcraft.action.spell.SpellSequence;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

/**
 * A spell that transforms a given target into a critter, rendering it
 * permanently uncontrollable. Prior to casting the spell caster is moved with
 * casting range.
 *
 * @author Blair Butterworth
 */
public class PolymorphSequence extends SpellSequence
{
    @Inject
    public PolymorphSequence(PolymorphSpell cast, MoveWithinCastingRangeAction move) {
        super(cast, move);
        move.setSpell(Spell.Polymorph);
        cast.setSpell(Spell.Polymorph);
        cast.setEffect(EffectType.Spell);
        cast.setProduct(UnitType.Sheep);
    }
}
