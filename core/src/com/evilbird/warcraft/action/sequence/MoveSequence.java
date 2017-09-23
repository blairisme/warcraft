package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.action.common.MoveAction;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class MoveSequence implements ActionProvider
{
    private ConfirmSequence confirmSequence;

    @Inject
    public MoveSequence(ConfirmSequence confirmSequence)
    {
        this.confirmSequence = confirmSequence;
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        ItemRoot root = item.getRoot();
        Vector2 position = root.unproject(input.getPosition());
        return getConfirmMoveAction(item, position);
    }

    private Action getConfirmMoveAction(Item item, Vector2 destination)
    {
        Action confirm = confirmSequence.get(item.getParent(), destination);
        Action move = getAnimatedMoveAction(item, destination);
        return new ParallelAction(confirm, move);
    }

    private Action getAnimatedMoveAction(Item item, Vector2 destination)
    {
        Action confirm = confirmSequence.get(item.getParent(), destination);
        Action animateMove = new AnimateAction((Animated)item, UnitAnimation.Move);
        Action move = new MoveAction((Movable)item, destination);
        Action animateIdle = new AnimateAction((Animated)item, UnitAnimation.Idle);
        return new SequenceAction(confirm, animateMove, move, animateIdle);
    }
}
