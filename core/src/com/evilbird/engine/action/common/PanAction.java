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

import javax.inject.Inject;

public class PanAction extends BasicAction
{
    private Vector2 delta;
    private Positionable positionable;

    @Inject
    public PanAction() {
    }

    public PanAction(Positionable positionable, Vector2 delta) {
        this.positionable = positionable;
        this.delta = delta;
    }

    public void setPositionable(Positionable positionable) {
        this.positionable = positionable;
    }

    public void setPositionDelta(Vector2 delta) {
        this.delta = delta;
    }

    @Override
    public boolean act(float time) {
        Vector2 value = positionable.getPosition();
        Vector2 difference = new Vector2(delta.x, delta.y);
        Vector2 result = value.add(difference);
        positionable.setPosition(result);
        return true;
    }
}
