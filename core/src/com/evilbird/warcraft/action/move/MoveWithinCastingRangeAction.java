/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.data.spell.Spell;

import javax.inject.Inject;

/**
 * Instances of this {@link Action action} move a {@link GameObject} from its
 * current location to within spell casting range of a given target. The moving
 * item will be animated with a movement animation, as well choosing a path
 * that avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveWithinCastingRangeAction extends MoveWithinRangeAction
{
    private transient Spell spell;

    @Inject
    public MoveWithinCastingRangeAction(MoveEvents events) {
        super(events);
    }

    @Override
    public boolean destinationValid() {
        return true;
    }

    @Override
    public int getRange() {
        return (int)spell.getCastRange();
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }
}