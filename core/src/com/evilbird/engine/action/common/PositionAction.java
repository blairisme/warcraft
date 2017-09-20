package com.evilbird.engine.action.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by blair on 15/09/2017.
 */
public class PositionAction extends Action
{
    private com.evilbird.engine.item.Positionable positionable;
    private Vector2 position;

    public PositionAction(com.evilbird.engine.item.Positionable positionable, Vector2 position)
    {
        this.positionable = positionable;
        this.position = position;
    }

    @Override
    public boolean act(float time)
    {
        positionable.setPosition(position);
        return true;
    }
}
