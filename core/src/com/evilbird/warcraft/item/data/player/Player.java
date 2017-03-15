package com.evilbird.warcraft.item.data.player;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemProperty;
import com.evilbird.engine.item.specialized.ResourceIdentifier;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Player extends ItemGroup
{
    private boolean consoleUser;
    private Map<ResourceIdentifier, Float> resources;

    @Inject
    public Player()
    {
        super.setPosition(0, 0);
        super.setSize(Float.MAX_VALUE, Float.MAX_VALUE);

        consoleUser = true;
        resources = new HashMap<ResourceIdentifier, Float>();
    }

    public boolean getConsoleUser()
    {
        return consoleUser;
    }

    public float getResource(ResourceIdentifier resource)
    {
        Float result = resources.get(resource);
        return result != null ? result : 0f;
    }

    public void setResource(ResourceIdentifier type, Float value)
    {
        this.resources.put(type, value);
    }

    @Override
    public void setSize(Vector2 size)
    {
        super.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    @Override
    public void setSize(float width, float height)
    {
        super.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    @Override
    public void setPosition(Vector2 position)
    {
        super.setPosition(0, 0);
    }

    @Override
    public void setPosition(float x, float y)
    {
        super.setPosition(0, 0);
    }

    @Override
    public Object getProperty(ItemProperty property)
    {
        if (property instanceof ResourceIdentifier){
            return getResource((ResourceIdentifier)property);
        }
        return super.getProperty(property);
    }

    @Override
    public void setProperty(ItemProperty property, Object value)
    {
        if (property instanceof ResourceIdentifier){
            setResource((ResourceIdentifier)property, (Float)value);
        }
        else{
            super.setProperty(property, value);
        }
    }
}
