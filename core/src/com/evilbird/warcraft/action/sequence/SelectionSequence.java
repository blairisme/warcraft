package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.framework.EmptyAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
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
    public Action get(ActionIdentifier action, Item item, Item target, UserInput input)
    {
        if (item.getSelectable())
        {
            return get(item, !item.getSelected());
        }
        return new EmptyAction();
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
