package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.data.camera.Zoomable;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ZoomActionProvider implements ActionProvider
{
    @Inject
    public ZoomActionProvider()
    {
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        return new ZoomAction((Zoomable)item, input);
    }
}
