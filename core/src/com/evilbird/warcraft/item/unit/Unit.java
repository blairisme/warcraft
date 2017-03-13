package com.evilbird.warcraft.item.unit;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.ItemProperty;
import com.evilbird.engine.item.control.AnimatedItem;
import com.evilbird.warcraft.action.ActionType;

import java.util.EnumSet;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Unit extends AnimatedItem
{
    private String name;
    private Drawable icon;
    private EnumSet<ActionType> actions;
    private float health;
    private float healthMaximum;

    @Inject
    public Unit()
    {
        actions = EnumSet.noneOf(ActionType.class);
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
        else{
            super.setProperty(property, value);
        }
    }
}
