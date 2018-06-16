package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public interface ActionProvider
{
    Action get(ActionIdentifier action, Item item, Item target, UserInput input);
}
