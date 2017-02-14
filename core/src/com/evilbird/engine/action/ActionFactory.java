package com.evilbird.engine.action;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.utility.Identifier;

public interface ActionFactory
{
    public void load(AssetManager assetManager);

    public Action newAction(Identifier action, Actor actor, Object value);
}
