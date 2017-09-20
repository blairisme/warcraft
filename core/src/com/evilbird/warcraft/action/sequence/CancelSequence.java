package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.RemoveAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class CancelSequence implements ActionProvider
{
    @Inject
    public CancelSequence()
    {
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        return new RemoveAction(item);
    }
}
