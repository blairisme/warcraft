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
 * A spell that slows a given targets movement and attack speeds for a period
 * of time. The spell is cast instantaneously and won't be repeated until cast
 * again. Prior to casting, the spell caster is moved with casting range.
 *
 * @author Blair Butterworth
 */
public class SlowSequence extends SpellSequence
{
    @Inject
    public SlowSequence(SlowSpell spell, MoveToItemAction move) {
        super(spell, move);
    }
}
