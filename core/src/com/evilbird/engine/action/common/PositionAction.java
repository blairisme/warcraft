package com.evilbird.engine.action.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Positionable;

public class PositionAction extends BasicAction
{
    private Positionable positionable;
    private Vector2 position;

    public PositionAction(Positionable positionable, Vector2 position) {
        this.positionable = positionable;
        this.position = position;
    }

    @Override
    public boolean act(float time) {
        positionable.setPosition(position);
        return true;
    }
}
