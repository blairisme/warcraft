package com.evilbird.warcraft.action.modifier;

import com.badlogic.gdx.math.Vector2;

public class MoveModifier implements ActionModifier
{
    private float speed;
    private Vector2 destination;
    private Vector2 direction;

    public MoveModifier(Vector2 destination, float speed)
    {
        this.speed = speed;
        this.destination = destination;
        restart();
    }

    @Override
    public Object modify(Object value, float time)
    {
        if (value instanceof Vector2)
        {
            Vector2 position = (Vector2)value;
            Vector2 direction = getDirection(position);
            Vector2 increment = direction.scl(time);
            Vector2 newPosition = position.cpy().add(increment);

            float previousDistance = destination.cpy().sub(position).len2();
            float newDistance = destination.cpy().sub(newPosition).len2();
            return (newDistance < previousDistance) ? newPosition : destination;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void restart()
    {
        this.direction = null;
    }

    private Vector2 getDirection(Vector2 position)
    {
        if (direction == null){
            direction = destination.cpy().sub(position).nor().scl(speed);
        }
        return new Vector2(direction);
    }
}
