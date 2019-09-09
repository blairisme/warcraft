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
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ACTION_COMPLETE;
import static com.evilbird.engine.action.ActionConstants.ACTION_INCOMPLETE;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;

/**
 * Instances of this {@link Action action} move an {@link Item}, animating it
 * with a movement animation, and choosing a path that avoids obstacles.
 *
 * @author Blair Butterworth
 */
public class MoveSequence extends DelegateAction
{
    @Inject
    public MoveSequence(MoveAction move) {
        super(move);
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (super.act(time)) {
            return complete();
        }
        return ACTION_INCOMPLETE;
    }
    private boolean initialized() {
        Unit unit = (Unit)getItem();
        return unit.getAnimation() == Move;
    }

    private boolean initialize() {
        Unit unit = (Unit)getItem();
        unit.setAnimation(Move);
        return ACTION_INCOMPLETE;
    }

    private boolean complete() {
        Unit unit = (Unit)getItem();
        unit.setAnimation(Idle);
        return ACTION_COMPLETE;
    }
}
