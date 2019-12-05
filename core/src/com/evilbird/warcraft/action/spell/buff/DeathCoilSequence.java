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
 * A spell that "transfers" health its target to the caster. Prior to casting,
 * the spell caster is moved with casting range.
 *
 * @author Blair Butterworth
 */
public class DeathCoilSequence extends SpellSequence
{
    @Inject
    public DeathCoilSequence(DeathCoilSpell spell, MoveToItemAction move) {
        super(spell, move);
    }
}
