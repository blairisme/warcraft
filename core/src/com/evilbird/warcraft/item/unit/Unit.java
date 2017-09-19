package com.evilbird.warcraft.item.unit;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.ItemProperty;
import com.evilbird.engine.item.specialized.animated.AnimatedItem;
import com.evilbird.engine.item.specialized.animated.ResourceIdentifier;
import com.evilbird.warcraft.action.ActionType;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Unit extends AnimatedItem implements Destructible
{
    private String name;
    private Drawable icon;
    private float health;
    private float healthMaximum;
    private EnumSet<ActionType> actions;
    private Map<ResourceIdentifier, Float> resources;

    @Inject
    public Unit()
    {
        name = "Unknown";
        icon = null;
        actions = EnumSet.noneOf(ActionType.class);
        health = 0;
        healthMaximum = 0;
        resources = new HashMap<ResourceIdentifier, Float>();
    }

    public EnumSet<ActionType> getActions()
    {
        return actions;
    }

    public float getHealth()
    {
        return health;
    }

    public float getHealthMaximum()
    {
        return healthMaximum;
    }

    public Drawable getIcon()
    {
        return icon;
    }

    public String getName()
    {
        return name;
    }

    public float getResource(ResourceIdentifier resource)
    {
        Float result = resources.get(resource);
        return result != null ? result : 0f;
    }

    public void setActions(EnumSet<ActionType> actions)
    {
        this.actions = actions;
    }

    public void setIcon(Drawable icon)
    {
        this.icon = icon;
    }

    public void setHealth(float health)
    {
        this.health = health;
    }

    public void setHealthMaximum(float healthMaximum)
    {
        this.healthMaximum = healthMaximum;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setResource(ResourceIdentifier resource, float value)
    {
        this.resources.put(resource, value);
    }

    @Override
    public Object getProperty(ItemProperty property)
    {
        if (UnitProperties.Actions.equals(property)){
            return getActions();
        }
        else if (UnitProperties.Health.equals(property)){
            return getHealth();
        }
        else if (UnitProperties.Icon.equals(property)){
            return getIcon();
        }
        else if (property instanceof ResourceIdentifier){
            return getResource((ResourceIdentifier)property);
        }
        return super.getProperty(property);
    }

    @Override
    public void setProperty(ItemProperty property, Object value)
    {
        if (UnitProperties.Actions.equals(property)){
            setActions((EnumSet<ActionType>)value);
        }
        else if (UnitProperties.Health.equals(property)){
            setHealth((Float)value);
        }
        else if (UnitProperties.Icon.equals(property)){
            setIcon((Drawable)value);
        }
        else if (property instanceof ResourceIdentifier){
            setResource((ResourceIdentifier)property, (Float)value);
        }
        else{
            super.setProperty(property, value);
        }
    }
}
