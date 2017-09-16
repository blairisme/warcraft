package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.replacement.Positionable;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

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
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        return new DragAction(item, input);
    }
}
