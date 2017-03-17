package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.InstantDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.ConstantModifier;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.AnimationProperty;
import com.evilbird.engine.item.specialized.SoundIdentifier;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
//TODO: Replace with utility
public class AudioActionProvider
{
    @Inject
    public AudioActionProvider()
    {
    }

    public Action get(Item item, SoundIdentifier sound)
    {
        ActionValue value = new ItemValue(item, AnimationProperty.Sound);
        ActionModifier modifier = new ConstantModifier(sound);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }
}
