package com.evilbird.warcraft.item.unit;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.item.specialized.animated.AnimatedItem;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.common.capability.Destructible;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;

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
    private Collection<ActionIdentifier> actions;

    @Inject
    public Unit()
    {
        name = "Unknown";
        icon = null;
        actions = Collections.emptyList();
        health = 0;
        healthMaximum = 0;
    }

    public Collection<ActionIdentifier> getAvailableActions()
    {
        return Collections.unmodifiableCollection(actions);
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

    public void setActions(Collection<ActionIdentifier> actions)
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
}
