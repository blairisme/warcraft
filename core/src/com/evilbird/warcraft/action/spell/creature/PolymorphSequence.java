/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.spell.SpellSequence;

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
    public PolymorphSequence(PolymorphSpell spell, MoveToItemAction move) {
        super(spell, move);
    }
}