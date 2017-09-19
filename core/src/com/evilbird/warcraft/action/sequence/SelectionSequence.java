package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.ParallelAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.InstantDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.ConstantModifier;
import com.evilbird.engine.action.replacement.AudibleAction;
import com.evilbird.engine.action.replacement.SelectAction;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperties;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.unit.UnitSound;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SelectionSequence implements ActionProvider
{
    @Inject
    public SelectionSequence()
    {
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        return get(item);
    }

    private Action get(Item item)
    {
        return get(item, !item.getSelected());
    }

    public Action get(Item item, boolean selected)
    {
        Action result = new SelectAction(item, selected);
        if (selected)
        {
            Action sound = new AudibleAction((Audible)item, UnitSound.Selected);
            result = new ParallelAction(result, sound);
        }
        return result;
    }
}
