/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.common.AnimatedMoveAction;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this class create {@link Action Actions} that instruct a given
 * {@link Item} to move to the given target following a path that conforms to
 * its movement capability.
 *
 * @author Blair Butterworth
 */
public class MoveFactory implements ActionProvider
{
    private MoveReporter moveReporter;
    private Provider<MoveSequence> movePathPool;
    private Provider<AnimateAction> moveCancelPool;

    @Inject
    public MoveFactory(
        MoveReporter moveReporter,
        Provider<MoveSequence> movePathPool,
        Provider<AnimateAction> moveCancelPool)
    {
        this.moveReporter = moveReporter;
        this.movePathPool = movePathPool;
        this.moveCancelPool = moveCancelPool;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        switch((MoveActions)action) {
            case MoveToLocation: return getMoveToLocationAction(context);
            case MoveCancel: return getMoveCancelAction(context);
            default: throw new UnsupportedOperationException();
        }
    }

    public Action getMoveToLocationAction(ActionContext context) {
        Item item = context.getItem();
        ItemRoot root = item.getRoot();
        UserInput input = context.getInput();
        Vector2 destination = root.unproject(input.getPosition());

        MoveSequence action = movePathPool.get();
        action.setItem(item);
        action.setDestination(destination);
        action.setObserver(moveReporter);

        return action;
    }

    public Action getMoveCancelAction(ActionContext context) {
        Item item = context.getItem();
        Action idleAnimation = new AnimateAction((Animated)item, UnitAnimation.Idle);
        return new ReplacementAction(item, idleAnimation);
    }
}
