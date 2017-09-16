package com.evilbird.engine.action.replacement;

import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by blair on 15/09/2017.
 */

public class PositionAction extends Action
{
    private Positionable positionable;

    public PositionAction(Positionable positionable)
    {
        this.positionable = positionable;
    }

    @Override
    public boolean act(float delta)
    {
        return false;
    }
}
