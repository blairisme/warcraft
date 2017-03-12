package com.evilbird.warcraft.item.world.unit;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.ItemProperty;
import com.evilbird.engine.item.control.AnimatedItem;
import com.evilbird.warcraft.action.ActionType;

import java.util.EnumSet;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
//TODO: Split into specialized classes
public class Unit extends AnimatedItem
{
    private Identifier owner;
    private String name;
    private Drawable icon;
    private EnumSet<ActionType> actions;

    private float armour;
    private float damageMinimum;
    private float damageMaximum;
    private float health;
    private float healthMaximum;
    private int level;
    private float speed;
    private float sight;
    private float range;

    private float gold;
    private float oil;
    private float wood;

    public Unit()
    {
    }

    public EnumSet<ActionType> getActions()
    {
        return actions;
    }

    public float getArmour()
    {
        return armour;
    }

    public float getDamageMinimum()
    {
        return damageMinimum;
    }

    public float getDamageMaximum()
    {
        return damageMaximum;
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

    public int getLevel()
    {
        return level;
    }

    public String getName()
    {
        return name;
    }

    public float getSpeed()
    {
        return speed;
    }

    public float getSight()
    {
        return sight;
    }

    public float getRange()
    {
        return range;
    }

    public void setActions(EnumSet<ActionType> actions)
    {
        this.actions = actions;
    }

    public void setArmour(float armour)
    {
        this.armour = armour;
    }

    public void setDamageMinimum(float damageMinimum)
    {
        this.damageMinimum = damageMinimum;
    }

    public void setDamageMaximum(float damageMaximum)
    {
        this.damageMaximum = damageMaximum;
    }

    public void setHealth(float health)
    {
        this.health = health;
    }

    public void setHealthMaximum(float healthMaximum)
    {
        this.healthMaximum = healthMaximum;
    }

    public void setIcon(Drawable icon)
    {
        this.icon = icon;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public void setSight(float sight)
    {
        this.sight = sight;
    }

    public void setRange(float range)
    {
        this.range = range;
    }

    public float getGold()
    {
        return gold;
    }

    public void setGold(float gold)
    {
        this.gold = gold;
    }

    public float getOil()
    {
        return oil;
    }

    public void setOil(float oil)
    {
        this.oil = oil;
    }

    public float getWood()
    {
        return wood;
    }

    public void setWood(float wood)
    {
        this.wood = wood;
    }

    public Identifier getOwner()
    {
        return owner;
    }

    public void setOwner(Identifier owner)
    {
        this.owner = owner;
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
        else if (UnitProperties.Gold.equals(property)){
            return getGold();
        }
        else if (UnitProperties.Wood.equals(property)){
            return getWood();
        }
        else if (UnitProperties.Owner.equals(property)){
            return getOwner();
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
        else if (UnitProperties.Gold.equals(property)){
            setGold((Float)value);
        }
        else if (UnitProperties.Wood.equals(property)){
            setWood((Float)value);
        }
        else if (UnitProperties.Owner.equals(property)){
            setOwner((Identifier)value);
        }
        else{
            super.setProperty(property, value);
        }
    }
}

