package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.common.capability.Zoomable;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ZoomSequence implements ActionProvider
{
    @Inject
    public ZoomSequence()
    {
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        return new com.evilbird.warcraft.action.common.ZoomAction((Zoomable)item, input);
    }
}
