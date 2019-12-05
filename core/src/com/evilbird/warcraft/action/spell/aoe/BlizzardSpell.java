/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.spell.SpellSequence;
import com.evilbird.warcraft.object.common.spell.Spell;
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
    public BlizzardSpell(AoeSpellAction spell, MoveToItemAction move) {
        super(spell, move);
        spell.setSpell(Spell.Blizzard);
        spell.setEffect(EffectType.Spell);
        spell.setProduct(UnitType.Blizzard);
    }
}
