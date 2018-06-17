package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

/**
 * Implementors of this interface provide a method that creates an action given
 * a type identifier and contextual information.
 *
 * @author Blair Butterworth
 */
public interface ActionProvider
{
    Action get(ActionIdentifier action, Item item, Item target, UserInput input);
}
