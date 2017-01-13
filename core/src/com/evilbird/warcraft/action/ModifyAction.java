package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.warcraft.action.duration.ActionDuration;
import com.evilbird.warcraft.action.modifier.ActionModifier;
import com.evilbird.warcraft.action.value.ActionValue;
import com.evilbird.warcraft.action.value.ItemValue;
import com.evilbird.warcraft.item.Item;
import com.evilbird.warcraft.utility.Identifier;

public class ModifyAction extends Action
{
    private ActionValue value;
    private ActionModifier modifier;
    private ActionDuration duration;

    //TODO remove
    public ModifyAction(Actor target, Identifier property, ActionModifier modifier, ActionDuration duration)
    {
        this(new ItemValue((Item)target, property), modifier, duration);
    }

    public ModifyAction(ActionValue value, ActionModifier modifier, ActionDuration duration)
    {
        this.value = value;
        this.modifier = modifier;
        this.duration = duration;
    }

    @Override
    public boolean act(float time)
    {
        Object oldValue = value.get();
        Object newValue = modifier.modify(oldValue, time);
        value.set(newValue);
        return duration.isComplete(time);
    }

    @Override
    public void restart()
    {
        modifier.restart();
        duration.restart();
    }
}
