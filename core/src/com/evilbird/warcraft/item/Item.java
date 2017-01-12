package com.evilbird.warcraft.item;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.evilbird.warcraft.utility.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Item extends Actor
{
    private static final Identifier SELECTED_PROPERTY = new Identifier("Selected");
    private static final Identifier POSITION_PROPERTY = new Identifier("Position");
    private Map<Identifier, Object> properties;

    public Item()
    {
        this.properties = new HashMap<Identifier, Object>();
    }

    public Item(Map<Identifier, Object> properties)
    {
        this.properties = properties;
    }

    public Vector2 getPosition(int alignment)
    {
        return new Vector2(getX(alignment), getY(alignment));
    }

    public void setPosition(Vector2 position, int alignment)
    {
        setPosition(position.x, position.y, alignment);
    }

    public boolean getSelected()
    {
        return (Boolean)properties.get(SELECTED_PROPERTY);
    }

    public void setSelected(boolean selected)
    {
        properties.put(SELECTED_PROPERTY, selected);
    }

    public Object getProperty(Identifier property)
    {
        if (Objects.equals(property, SELECTED_PROPERTY))
        {
            return getSelected();
        }
        if (Objects.equals(property, POSITION_PROPERTY))
        {
            return getPosition(Align.center);
        }
        return properties.get(property);
    }

    public void setProperty(Identifier property, Object value)
    {
        if (Objects.equals(property, SELECTED_PROPERTY))
        {
            setSelected((Boolean) value);
        }
        if (Objects.equals(property, POSITION_PROPERTY))
        {
            setPosition((Vector2)value, Align.center);
        }
        else
        {
            properties.put(property, value);
        }
    }
}
