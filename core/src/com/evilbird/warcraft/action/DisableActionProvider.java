package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.InstantDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.ConstantModifier;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperties;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
//TODO: Replace with utility
public class DisableActionProvider
{
    @Inject
    public DisableActionProvider()
    {
    }

    public Action get(Item item, boolean touchable)
    {
        ActionValue value = new ItemValue(item, ItemProperties.Touchable);
        ActionModifier modifier = new ConstantModifier(touchable ? Touchable.enabled : Touchable.disabled);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }
}
