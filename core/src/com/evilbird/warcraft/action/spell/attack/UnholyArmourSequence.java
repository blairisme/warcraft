/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.spell.SpellSequence;

import javax.inject.Inject;

/**
 * A spell that surrounds a target with a unholy armour, making it invulnerable
 * for a period of time. This spell is cast instantaneously and requires that
 * the target lose a portion of its health to receive the armour. Prior to
 * casting, the spell caster is moved with casting range.
 *
 * @author Blair Butterworth
 */
public class UnholyArmourSequence extends SpellSequence
{
    @Inject
    public UnholyArmourSequence(UnholyArmourSpell spell, MoveToItemAction move) {
        super(spell, move);
    }
}
