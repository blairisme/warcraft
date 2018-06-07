package com.evilbird.warcraft.item.data.player;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.data.DataType;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Player extends ItemGroup implements ResourceContainer
{
    private boolean consoleUser;
    private Map<ResourceIdentifier, Float> resources;

    @Inject
    public Player()
    {
        super.setPosition(0, 0);
        super.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
        super.setType(DataType.Player);

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

    public void setResource(ResourceIdentifier type, float value)
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
}
