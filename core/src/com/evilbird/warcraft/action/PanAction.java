package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.replacement.Positionable;
import com.evilbird.engine.device.UserInput;

/**
 * Created by blair on 16/09/2017.
 */
public class PanAction extends Action
{
    private Positionable positionable;
    private UserInput input;

    public PanAction(Positionable positionable, UserInput input)
    {
        this.positionable = positionable;
        this.input = input;
    }

    @Override
    public boolean act(float time)
    {
        Vector2 value = positionable.getPosition();
        Vector2 delta = input.getDelta();
        Vector2 difference = new Vector2(delta.x, delta.y);
        Vector2 result = value.add(difference);
        positionable.setPosition(result);
        return true;
    }
}
