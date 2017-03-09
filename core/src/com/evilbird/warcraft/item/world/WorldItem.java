package com.evilbird.warcraft.item.world;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.control.AnimatedItem;
import com.evilbird.warcraft.action.ActionType;

import java.util.EnumSet;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class WorldItem extends AnimatedItem
{
    private String owner;
    private String name;
    private Drawable icon;
    private EnumSet<ActionType> actions;
    private float health;
    private float healthMaximum;
    private float gold;
    private float oil;
    private float wood;

    @Inject
    public WorldItem()
    {
        actions = EnumSet.noneOf(ActionType.class);
    }

    public EnumSet<ActionType> getActions()
    {
        return actions;
    }

    public Drawable getIcon()
    {
        return icon;
    }

    public String getName()
    {
        return name;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setActions(EnumSet<ActionType> actions)
    {
        this.actions = actions;
    }

    public void setIcon(Drawable icon)
    {
        this.icon = icon;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }
}
