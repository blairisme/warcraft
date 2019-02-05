/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.action.move.MoveAction;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.unit.UnitAnimation;

/**
 * Instances of this class represent an {@link Action} that moves a given
 * {@link Item} as well as displaying the <code>Items</code> move animation.
 *
 * @author Blair Butterworth
 */
//TODO: Replace with Animated Action
public class AnimatedMoveAction extends DelegateAction
{
    public AnimatedMoveAction(Item item, Item destination) {
        this(item, new MoveAction((Movable)item, destination));
    }

    public AnimatedMoveAction(Item item, Vector2 destination) {
        this(item, new MoveAction((Movable)item, destination));
    }

    private AnimatedMoveAction(Item item, Action move) {
        Action animate = new AnimateAction((Animated)item, UnitAnimation.Move);
        Action initial = new ParallelAction(animate, move);
        Action complete = new AnimateAction((Animated)item, UnitAnimation.Idle);
        delegate = new SequenceAction(initial, complete);
    }

    @Override
    public boolean act(float delta) {
        return delegate.act(delta);
    }
}
