package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

public interface ActionFactory
{
    void load();

    Action newAction(ActionIdentifier action, Item item, Item target, UserInput input);
}
