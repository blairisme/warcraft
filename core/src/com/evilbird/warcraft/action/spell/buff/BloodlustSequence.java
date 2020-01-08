/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
