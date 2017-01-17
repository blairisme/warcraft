package com.evilbird.warcraft.action.modifier;

import com.badlogic.gdx.math.Vector2;

//TODO - Refactor specialized implementations into separate classes
public class DeltaModifier implements ActionModifier
{
    private Object delta;
    private DeltaType type;
    private Object lowerBound;
    private Object upperBound;

    public DeltaModifier(Object delta, DeltaType type)
    {
        this(delta, type, null, null);
    }

    public DeltaModifier(Object delta, DeltaType type, Object lowerBound, Object upperBound)
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
            return valueVector.add(deltaVector);
        }
        else if (value instanceof Float)
        {
            Float valueFloat = value != null ? (Float)value : 0;
            Float deltaFloat = (Float)delta;
            Float result =  valueFloat + deltaFloat;

            if (lowerBound != null && result <= (Float)lowerBound){
                result = (Float)lowerBound;
            }
            if (upperBound != null && result >= (Float)upperBound){
                result = (Float)upperBound;
            }
            return result;
        }
        else if (value instanceof Integer)
        {
            Integer valueInteger = (Integer)value;
            Integer deltaInteger = (Integer)delta;
            Integer result = valueInteger + deltaInteger;

            if (lowerBound != null && result <= (Integer)lowerBound){
                result = (Integer)lowerBound;
            }
            if (upperBound != null && result >= (Integer)upperBound){
                result = (Integer)upperBound;
            }
            return result;
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
