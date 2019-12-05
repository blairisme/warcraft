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
 * A spell that surrounds a target with a flame shield, degrading the health of
 * any game objects adjacent to it. Flame shield is temporary effect that wears
 * off after a period of time. Prior to casting, the spell caster is moved with
 * casting range.
 *
 * @author Blair Butterworth
 */
public class FlameShieldSequence extends SpellSequence
{
    @Inject
    public FlameShieldSequence(FlameShieldSpell spell, MoveToItemAction move) {
        super(spell, move);
    }
}
