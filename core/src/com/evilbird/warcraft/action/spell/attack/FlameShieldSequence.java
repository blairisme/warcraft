/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
