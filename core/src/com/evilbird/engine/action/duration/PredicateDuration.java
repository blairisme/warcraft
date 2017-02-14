package com.evilbird.engine.action.duration;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.item.Unit;

import java.util.Objects;

public class PredicateDuration implements ActionDuration
{
    private Actor actor;
    private Identifier property;
    private Object value;

    public PredicateDuration(Actor actor, Identifier property, Object value)
    {
        this.actor = actor;
        this.property = property;
        this.value = value;
    }

    @Override
    public boolean isComplete(float time)
    {
        if (actor instanceof Unit)
        {
            Unit unit = (Unit)actor;
            Object actorValue = unit.getProperty(property);
            return Objects.equals(actorValue, value);
        }
        return true;
    }

    @Override
    public void restart()
    {
    }
}
