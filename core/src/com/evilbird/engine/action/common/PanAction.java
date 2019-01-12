/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Positionable;

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
