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
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Positionable;
import com.evilbird.engine.device.UserInput;

public class DragAction extends BasicAction
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
