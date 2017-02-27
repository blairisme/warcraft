package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public interface ActionFactory
{
    public void load();

    public Action newAction(ActionIdentifier action, Actor actor, Object value);

    public Action newAction(ActionContext context);
}
