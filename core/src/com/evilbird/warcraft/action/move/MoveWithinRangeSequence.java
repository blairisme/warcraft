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
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to within attack range of a given target. The moving item
 * will be animated with a movement animation, as well choosing a path that
 * avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveWithinRangeSequence extends MoveSequence
{
    @Inject
    public MoveWithinRangeSequence(MoveWithinRangeAction move) {
        super(move);
        setIdentifier(MoveActions.MoveWithRange);
    }
}
