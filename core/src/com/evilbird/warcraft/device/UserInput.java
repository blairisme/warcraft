package com.evilbird.warcraft.device;

import com.badlogic.gdx.math.Vector2;

public class UserInput
{
    private UserInputType type;
    private Vector2 position;
    private Vector2 delta;

    public UserInput(UserInputType type, Vector2 position)
    {
        this(type, position, Vector2.Zero);
    }

    public UserInput(UserInputType type, Vector2 position, Vector2 delta)
    {
        this.type = type;
        this.position = position;
        this.delta = delta;
    }

    public UserInputType getType()
    {
        return type;
    }

    public Vector2 getPosition()
    {
        return this.position;
    }

    public Vector2 getDelta()
    {
        return this.delta;
    }
}
