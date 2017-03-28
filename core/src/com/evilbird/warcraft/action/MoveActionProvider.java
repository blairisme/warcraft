package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.ParallelAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.PredicateDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.MoveModifier;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperties;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.common.ConfirmActionProvider;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class MoveActionProvider implements ActionProvider
{
    private AnimateActionProvider animateActionProvider;
    private ConfirmActionProvider confirmActionProvider;

    @Inject
    public MoveActionProvider(
        AnimateActionProvider animateActionProvider,
        ConfirmActionProvider confirmActionProvider)
    {
        this.animateActionProvider = animateActionProvider;
        this.confirmActionProvider = confirmActionProvider;
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        ItemRoot root = item.getRoot();
        Vector2 position = root.unproject(input.getPosition());
        return getConfirmMoveAction(item, position);
    }

    public Action get(Item item, Item destination)
    {
        return getAnimatedMoveAction(item, destination.getPosition());
    }

    public Action get(Item item, Vector2 destination)
    {
        return getAnimatedMoveAction(item, destination);
    }

    private Action getConfirmMoveAction(Item item, Vector2 destination)
    {
        Action confirm = confirmActionProvider.get(item.getParent(), destination);
        Action move = getAnimatedMoveAction(item, destination);
        return new ParallelAction(confirm, move);
    }

    private Action getAnimatedMoveAction(Item item, Vector2 destination)
    {
        Action confirm = confirmActionProvider.get(item.getParent(), destination);
        Action animateMove = animateActionProvider.get(item, UnitAnimation.Move);
        Action move = getMoveAction(item, destination);
        Action animateIdle = animateActionProvider.get(item, UnitAnimation.Idle);
        return new SequenceAction(confirm, animateMove, move, animateIdle);
    }

    private Action getMoveAction(Item item, Vector2 destination)
    {
        ActionValue value = new ItemValue(item, ItemProperties.Position);
        ActionModifier modifier = new MoveModifier(item, destination);
        ActionDuration duration = new PredicateDuration(item, ItemProperties.Position, destination);
        return new ModifyAction(value, modifier, duration);
    }
}
