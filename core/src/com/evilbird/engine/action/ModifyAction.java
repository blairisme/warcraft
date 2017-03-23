package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.value.ActionValue;

import java.util.concurrent.CancellationException;

public class ModifyAction extends Action
{
    private ActionValue value;
    private ActionModifier modifier;
    private ActionDuration duration;

    public ModifyAction(ActionValue value, ActionModifier modifier, ActionDuration duration)
    {
        this.value = value;
        this.modifier = modifier;
        this.duration = duration;
    }

    @Override
    public boolean act(float time)
    {
        try
        {
            Object oldValue = value.get();
            Object newValue = modifier.modify(oldValue, time);
            value.set(newValue);
            return duration.isComplete(time);
        }
        catch (CancellationException e)
        {
            System.out.print("Modify operation cancelled");
            return true;
        }
    }

    @Override
    public void restart()
    {
        modifier.restart();
        duration.restart();
    }
}
