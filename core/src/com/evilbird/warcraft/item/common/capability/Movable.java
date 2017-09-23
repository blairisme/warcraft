package com.evilbird.warcraft.item.common.capability;

import com.evilbird.engine.item.Positionable;

/**
 * Created by blair on 13/09/2017.
 */
public interface Movable extends Positionable
{
    public float getMovementSpeed();

    public void setMovementSpeed(float speed);
}
