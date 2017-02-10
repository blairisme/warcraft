package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.Identifier;

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
