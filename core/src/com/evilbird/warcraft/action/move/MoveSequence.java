/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import javax.inject.Inject;

public class MoveSequence extends DelegateAction
{
    private MoveAction move;
    private AnimateAction moveAnimation;
    private AnimateAction idleAnimation;
    private ReplacementAction replacement;

    @Inject
    public MoveSequence() {
        move = new MoveAction();
        moveAnimation = new AnimateAction(UnitAnimation.Move);
        idleAnimation = new AnimateAction(UnitAnimation.Idle);

        Action initial = new ParallelAction(move, moveAnimation);
        Action sequence = new SequenceAction(initial, idleAnimation);
        replacement = new ReplacementAction(sequence);

        delegate = replacement;
    }

    public void setItem(Item item) {
        move.setItem((Movable)item);
        moveAnimation.setItem((Animated)item);
        idleAnimation.setItem((Animated)item);
        replacement.setItem(item);
    }

    public void setDestination(Item destination) {
        move.setDestination(destination);
    }

    public void setDestination(Vector2 destination) {
        move.setDestination(destination);
    }

    public void setObserver(MoveObserver observer) {
        move.setObserver(observer);
    }

    @Override
    public boolean act(float delta) {
        return delegate.act(delta);
    }
}
