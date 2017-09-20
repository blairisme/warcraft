package com.evilbird.engine.item;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by blair on 15/09/2017.
 */

public interface Positionable extends Parented
{
    public Vector2 getPosition();

    public void setPosition(Vector2 position);
}
