package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;

public class RepeatedAction extends Action
{
    private Action action;

    public RepeatedAction(Action action)
    {
        this.action = action;
    }

    @Override
    public boolean act(float delta)
    {
        if (action.act(delta))
        {
            action.restart();
        }
        return false;
    }
}
