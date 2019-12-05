/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.spell.SpellSequence;

import javax.inject.Inject;

/**
 * A spell that increases the speed for a given targets movement and attack.
 * The spell is cast instantaneously and wears off after a period of time.
 * Prior to casting, the spell caster is moved with casting range.
 *
 * @author Blair Butterworth
 */
public class HasteSequence extends SpellSequence
{
    @Inject
    public HasteSequence(HasteSpell spell, MoveToItemAction move) {
        super(spell, move);
    }
}
