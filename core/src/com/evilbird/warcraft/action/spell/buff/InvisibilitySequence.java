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
 * A spell that renders its target invisible to enemy players. The target
 * remains invisible until it moves or attacks. The spell is cast
 * instantaneously and won't be repeated until cast again. Prior to casting,
 * the spell caster is moved with casting range.
 *
 * @author Blair Butterworth
 */
public class InvisibilitySequence extends SpellSequence
{
    @Inject
    public InvisibilitySequence(InvisibilitySpell spell, MoveToItemAction move) {
        super(spell, move);
    }
}
