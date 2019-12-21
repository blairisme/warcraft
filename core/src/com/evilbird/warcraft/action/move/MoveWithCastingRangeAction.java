/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
public class MoveWithCastingRangeAction extends MoveWithinRangeAction
{
    private transient Spell spell;

    @Inject
    public MoveWithCastingRangeAction(MoveEvents events) {
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