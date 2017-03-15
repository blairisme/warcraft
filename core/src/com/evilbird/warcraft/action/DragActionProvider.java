package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.InstantDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.DeltaModifier;
import com.evilbird.engine.action.modifier.DeltaType;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperties;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class DragActionProvider implements ActionProvider
{
    @Inject
    public DragActionProvider()
    {
    }

    @Override
    public Action get(Item item, Item target, UserInput input)
    {
        Vector2 inputDelta = input.getDelta();
        Vector2 dragDelta = new Vector2(inputDelta.x * -1, inputDelta.y * -1);

        ActionValue value = new ItemValue(target, ItemProperties.Position);
        ActionModifier modifier = new DeltaModifier(dragDelta, DeltaType.PerUpdate);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }
}
