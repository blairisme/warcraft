package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.item.Positionable;
import com.evilbird.engine.device.UserInput;

/**
 * Created by blair on 16/09/2017.
 */
public class DragAction extends Action
{
    private Positionable positionable;
    private UserInput input;

    public DragAction(Positionable positionable, UserInput input)
    {
        this.positionable = positionable;
        this.input = input;
    }

    @Override
    public boolean act(float time)
    {
        Vector2 value = positionable.getPosition();
        Vector2 delta = input.getDelta();
        Vector2 difference = new Vector2(delta.x * -1, delta.y * -1);
        Vector2 result = value.add(difference);
        positionable.setPosition(result);
        return true;
    }
}
