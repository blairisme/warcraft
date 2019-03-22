/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.badlogic.gdx.math.Vector2;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.move.MoveActions.MoveToLocation;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination, specified as a {@link Vector2}. The
 * moving item will be animated with a movement animation.
 *
 * @author Blair Butterworth
 */
public class MoveToVectorSequence extends DelegateAction
{
    private transient MoveAction move;

    @Inject
    public MoveToVectorSequence() {
        setIdentifier(MoveToLocation);
        move = new MoveToVectorAction();
        Action moveAnimation = new AnimateAction(UnitAnimation.Move);
        Action initial = new ParallelAction(move, moveAnimation);
        Action idleAnimation = new AnimateAction(UnitAnimation.Idle);
        delegate = new SequenceAction(initial, idleAnimation);
    }

    @Override
    public void setCause(UserInput cause) {
        super.setCause(cause);
    }

    public void setObserver(MoveObserver observer) {
        move.setObserver(observer);
    }
}
