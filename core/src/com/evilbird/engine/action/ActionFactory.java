package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.item.Item;

public interface ActionFactory
{
    public void load();

    public Action newAction(ActionIdentifier action, Item actor, Object value);

    //public void assignAction(ActionIdentifier action, Item actor, Object value);
}
