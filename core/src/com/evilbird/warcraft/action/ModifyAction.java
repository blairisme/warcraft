package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.warcraft.action.duration.ActionDuration;
import com.evilbird.warcraft.action.modifier.ActionModifier;
import com.evilbird.warcraft.item.Item;
import com.evilbird.warcraft.utility.Identifier;

public class ModifyAction extends Action
{
    private Identifier property;
    private ActionModifier modifier;
    private ActionDuration duration;

    public ModifyAction(Actor target, Identifier property, ActionModifier modifier, ActionDuration duration)
    {
        this.actor = target;
        this.target = target;
        this.property = property;
        this.modifier = modifier;
        this.duration = duration;
    }

    @Override
    public boolean act(float time)
    {
        Actor target = getTarget();
        if (target instanceof Item)
        {
            Item item = (Item)target;
            Object oldValue = item.getProperty(property);
            Object newValue = modifier.modify(oldValue, time);
            item.setProperty(property, newValue);
            return duration.isComplete(time);
        }
        return false;
    }

    @Override
    public void restart()
    {
        modifier.restart();
        duration.restart();
    }
}
