package com.evilbird.engine.action.modifier;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

import java.util.ArrayList;
import java.util.Collection;

//TODO: Implement moving around other items.
//TODO: Implement movement restricted to certain terrains. E.g., land, water, air.
public class MoveModifier implements ActionModifier
{
    private ItemRoot root;
    private float speed;
    private float radius;
    private Vector2 destination;
    private Vector2 direction;
    private Collection<Identifier> capability;

    public MoveModifier(Item item, Vector2 destination)
    {
        this.root = item.getRoot();
        this.speed = 64f; //TODO: Obtain from item
        this.radius = 46f; //TODO: Obtain from item
        this.capability = new ArrayList<Identifier>(); //TODO: Obtain from item
        this.capability.add(new NamedIdentifier("Map")); //TODO: Obtain from item
        this.destination = destination;
        //destination.x = Math.round(destination.x);
        //destination.y = Math.round(destination.y);
        restart();
    }

    @Override
    public Object modify(Object value, float time)
    {
        if (value instanceof Vector2)
        {
            Vector2 position = (Vector2)value;
            Vector2 direction = getDirection(position, time);

            if (direction != null)
            {
                Vector2 increment = direction.cpy().scl(time * speed);
                //Vector2 remaining = destination.cpy().sub(position);
                //Vector2 increment = new Vector2(Math.min(movement.x, remaining.x), Math.min(movement.y, remaining.y));
                Vector2 newPosition = position.cpy().add(increment);

                //float previousDistance = destination.cpy().sub(position).len2();
                //float newDistance = destination.cpy().sub(newPosition).len2();
                //return (newDistance < previousDistance) ? newPosition : destination;

                return newPosition;
            }
            return value;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void restart()
    {
        this.direction = null;
    }

    private Vector2 getDirection(Vector2 position, float time)
    {
        //if (direction == null)
        {
            direction = destination.cpy().sub(position).nor();
        }
        if (! directionTraversable(position, direction, time))
        {
            direction = getAlternativeDirection(position, direction, time);
        }
        return direction;
    }

    private Vector2 getAlternativeDirection(Vector2 position, Vector2 direction, float time)
    {
        for (int attempt = 0; attempt < 8; attempt++)
        {
            direction.rotateRad(0.25f);

            if (directionTraversable(position, direction, time))
            {
                return direction;
            }
        }
        return null;
    }

    private boolean directionTraversable(Vector2 position, Vector2 direction, float time)
    {
        if (direction != null)
        {
            Vector2 timeIncrement = scale(direction, time * speed);
            Vector2 sizeIncrement = scale(direction, radius);

            Vector2 coordinates = new Vector2(position);
            //coordinates.add(radius, radius);
            coordinates.add(timeIncrement);
            coordinates.add(sizeIncrement);

            Item traversed = root.hit(coordinates, true);
            Identifier traversedType = traversed != null ? traversed.getType() : null;

            return capability.contains(traversedType);
        }
        return false;
    }

    public Vector2 scale(Vector2 vector, float factor)
    {
        Vector2 result = new Vector2(vector);
        result.scl(factor);
        return result;
    }
}
