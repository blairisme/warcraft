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
