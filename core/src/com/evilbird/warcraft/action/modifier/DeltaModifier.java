package com.evilbird.warcraft.action.modifier;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.warcraft.action.value.ActionValue;
import com.evilbird.warcraft.action.value.ObjectValue;

//TODO - Rename to increment modifier
public class DeltaModifier implements ActionModifier
{
    private ActionValue delta;
    private DeltaType type;
    private ActionValue lowerBound;
    private ActionValue upperBound;

    public DeltaModifier(Object delta, DeltaType type)
    {
        this(delta, type, null, null);
    }

    public DeltaModifier(Object delta, DeltaType type, Object lowerBound, Object upperBound)
    {
        this(new ObjectValue(delta), type, new ObjectValue(lowerBound), new ObjectValue(upperBound));
    }

    public DeltaModifier(ActionValue delta, DeltaType type, ActionValue lowerBound, ActionValue upperBound)
    {
        this.delta = delta;
        this.type = type;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Object modify(Object value, float time)
    {
        Object delta = getDelta(time);
        if (value instanceof Vector2)
        {
            Vector2 valueVector = value != null ? (Vector2)value : new Vector2(0, 0);
            Vector2 deltaVector = (Vector2)delta;
            Vector2 result = valueVector.add(deltaVector);
            return clamp(result);
        }
        else if (value instanceof Float)
        {
            Float valueFloat = value != null ? (Float)value : 0;
            Float deltaFloat = (Float)delta;
            Float result =  valueFloat + deltaFloat;
            return clamp(result);
        }
        else if (value instanceof Integer)
        {
            Integer valueInteger = (Integer)value;
            Integer deltaInteger = (Integer)delta;
            Integer result = valueInteger + deltaInteger;
            return clamp(result);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void restart()
    {
    }

    private Object getDelta(float time)
    {
        Object deltaValue = delta.get();

        if (type == DeltaType.PerSecond)
        {
            if (deltaValue instanceof Float)
            {
                return (Float)deltaValue * time;
            }
            throw new UnsupportedOperationException();
        }
        return deltaValue;
    }

    private Object clamp(Object value)
    {
        Object lowerValue = lowerBound.get();
        Object upperValue = upperBound.get();

        if (lowerValue != null && upperValue != null)
        {
            if (value instanceof Vector2)
            {
                Vector2 result = (Vector2)value;
                Vector2 min = (Vector2)lowerValue;
                Vector2 max = (Vector2)upperValue;
                result.x = MathUtils.clamp(result.x, min.x, max.x);
                result.y = MathUtils.clamp(result.y, min.y, max.y);
                return result;
            }
            else if (value instanceof Float)
            {
                return MathUtils.clamp((Float)value, (Float)lowerValue, (Float)upperValue);
            }
            else if (value instanceof Integer)
            {
                return MathUtils.clamp((Integer)value, (Integer)lowerValue, (Integer)upperValue);
            }
        }
        return value;
    }
}
