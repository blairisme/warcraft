package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.component.DragAction;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class DragSequence implements ActionProvider
{
    @Inject
    public DragSequence()
    {
    }

    @Override
    public Action get(ActionIdentifier action, Item item, Item target, UserInput input)
    {
        return new DragAction(item, input);
    }
}
