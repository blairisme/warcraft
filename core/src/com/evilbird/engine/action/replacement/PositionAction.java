package com.evilbird.engine.action.replacement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by blair on 15/09/2017.
 */
public class PositionAction extends Action
{
    private Positionable positionable;
    private Vector2 position;

    public PositionAction(Positionable positionable, Vector2 position)
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
