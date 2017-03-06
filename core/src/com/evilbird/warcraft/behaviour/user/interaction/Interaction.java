package com.evilbird.warcraft.behaviour.user.interaction;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

/**
 * Implementors of this TODO:Finish
 *
 * @author Blair Butterworth
 */
public interface Interaction
{
    boolean update(UserInput input, Item target, Item worldSelection, Item hudSelection);
}
