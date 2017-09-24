package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.ClearAction;
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
import com.evilbird.warcraft.action.common.ReplacementAction;
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
        Action confirm = confirmSequence.get(item, input);
        Action sequence = move(item, getDestination(item, input));
        Action move = new ParallelAction(confirm, sequence);
        return new ReplacementAction(item, move);
    }

    private Action move(Item item, Vector2 destination)
    {
        Action animateMove = new AnimateAction((Animated)item, UnitAnimation.Move);
        Action move = new MoveAction((Movable)item, destination);
        Action animateIdle = new AnimateAction((Animated)item, UnitAnimation.Idle);
        return new SequenceAction(animateMove, move, animateIdle);
    }

    private Vector2 getDestination(Item item, UserInput input)
    {
        ItemRoot root = item.getRoot();
        return root.unproject(input.getPosition());
    }
}
