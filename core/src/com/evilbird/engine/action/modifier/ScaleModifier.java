package com.evilbird.engine.action.modifier;

import com.badlogic.gdx.math.MathUtils;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ObjectValue;

public class ScaleModifier implements ActionModifier
{
    private ActionValue delta;
    private ActionValue lowerBound;
    private ActionValue upperBound;

    public ScaleModifier(Object delta, Object lowerBound, Object upperBound)
    {
        this(new ObjectValue(delta), new ObjectValue(lowerBound), new ObjectValue(upperBound));
    }

    public ScaleModifier(ActionValue delta, ActionValue lowerBound, ActionValue upperBound)
    {
        this.delta = delta;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Object modify(Object value, float time)
    {
        if (value instanceof Float)
        {
            Float valueFloat = value != null ? (Float)value : 0;
            Float deltaFloat = (Float)delta.get();
            Float result =  valueFloat * deltaFloat;
            return MathUtils.clamp(result, (Float)lowerBound.get(), (Float)upperBound.get());
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void restart()
    {
    }
}
