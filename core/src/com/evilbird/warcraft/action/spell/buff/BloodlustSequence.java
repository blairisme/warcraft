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
 * A spell that increases the attack damage of a given combatant. The spell is
 * cast instantaneously and its effects wear of after a period of time. Prior
 * to casting, the spell caster is moved with casting range.
 *
 * @author Blair Butterworth
 */
public class BloodlustSequence extends SpellSequence
{
    @Inject
    public BloodlustSequence(BloodlustSpell spell, MoveToItemAction move) {
        super(spell, move);
    }
}
