package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.PredicateDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.MoveModifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperties;
import com.evilbird.engine.item.ItemProperty;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class MoveActionProvider implements ActionProvider
{
    private AnimateActionProvider animateActionProvider;

    @Inject
    public MoveActionProvider(AnimateActionProvider animateActionProvider)
    {
        this.animateActionProvider = animateActionProvider;
    }

    @Override
    public Action get(Item item, Item target, UserInput input)
    {
        ItemRoot root = item.getRoot();
        Vector2 position = root.unproject(input.getPosition());
        return newAnimatedMove(item, position);
    }

    public Action get(Item item, Item destination)
    {
        return newAnimatedMove(item, destination.getPosition());
    }

    public Action get(Item item, Vector2 destination)
    {
        return newAnimatedMove(item, destination);
    }

    private Action newAnimatedMove(Item item, Vector2 destination)
    {
        Action animateMove = animateActionProvider.get(item, UnitAnimation.Move);
        Action move = newMoveAction(item, destination);
        Action animateIdle = animateActionProvider.get(item, UnitAnimation.Idle);
        return new SequenceAction(Arrays.asList(animateMove, move, animateIdle));
    }

    private Action newMoveAction(Item item, Vector2 destination)
    {
        ItemProperty position = ItemProperties.Position;
        ActionModifier modifier = new MoveModifier(destination, 64f);
        ActionDuration duration = new PredicateDuration(item, position, destination);
        return new ModifyAction(item, position, modifier, duration);
    }
}
