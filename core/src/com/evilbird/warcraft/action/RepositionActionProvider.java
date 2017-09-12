package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ModifyAction;
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
 * Created by blair on 12/09/2017.
 */

/* package */ class RepositionActionProvider
{
    private AnimateActionProvider animateActionProvider;

    @Inject
    public RepositionActionProvider(AnimateActionProvider animateActionProvider)
    {
        this.animateActionProvider = animateActionProvider;
    }

    public Action get(Item item, Item destination)
    {
        Action animate = animateActionProvider.get(item, UnitAnimation.Move);
        Action reposition = getMoveAction(item, destination.getPosition());
        Action idle = animateActionProvider.get(item, UnitAnimation.Idle);
        return new SequenceAction(animate, reposition, idle);
    }

    private Action getMoveAction(Item item, Vector2 destination)
    {
        ActionValue value = new ItemValue(item, ItemProperties.Position);
        ActionModifier modifier = new MoveModifier(item, destination, true);
        ActionDuration duration = new PredicateDuration(item, ItemProperties.Position, destination);
        return new ModifyAction(value, modifier, duration);
    }
}
