package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class BasicAction extends Action
{
    public void cancel() {
        Actor actor = getActor();
        actor.clearActions();
    }
}
