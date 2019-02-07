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

public class PositionAction extends BasicAction
{
    private Vector2 position;

    public PositionAction() {
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public boolean act(float time) {
        Positionable positionable = getItem();
        positionable.setPosition(position);
        return true;
    }
}
