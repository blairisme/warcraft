package com.evilbird.warcraft.action.modifier;

import com.badlogic.gdx.math.Vector2;

public class DeltaModifier implements ActionModifier
{
    private Object delta;
    private DeltaType type;

    public DeltaModifier(Object delta, DeltaType type)
    {
        this.delta = delta;
        this.type = type;
    }

    @Override
    public Object modify(Object value, float time)
    {
        Object delta = getDelta(time);
        if (value instanceof Vector2)
        {
            Vector2 valueVector = value != null ? (Vector2)value : new Vector2(0, 0);
            Vector2 deltaVector = (Vector2)delta;
            return valueVector.add(deltaVector);
        }
        else if (value instanceof Float)
        {
            Float valueFloat = value != null ? (Float)value : 0;
            Float deltaFloat = (Float)delta;
            return valueFloat + deltaFloat;
        }
        else if (value instanceof Integer)
        {
            Integer valueInteger = (Integer)value;
            Integer deltaInteger = (Integer)delta;
            return valueInteger + deltaInteger;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void restart()
    {
    }

    private Object getDelta(float time)
    {
        if (type == DeltaType.PerSecond)
        {
            if (delta instanceof Float)
            {
                return (Float)delta * time;
            }
            throw new UnsupportedOperationException();
        }
        return delta;
    }
}
