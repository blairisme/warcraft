package com.evilbird.warcraft.item.unit;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.replacement.Positionable;
import com.evilbird.engine.action.value.ActionValue;

/**
 * Created by blair on 13/09/2017.
 */
public interface Movable extends Positionable
{
    public float getMovementSpeed();

    public void setMovementSpeed(float speed);
}
