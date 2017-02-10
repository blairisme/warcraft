package com.evilbird.engine.action.modifier;

import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ObjectValue;

public class ConstantModifier implements ActionModifier
{
    private ActionValue newValue;

    public ConstantModifier(Object newValue)
    {
        this(new ObjectValue(newValue));
    }

    public ConstantModifier(ActionValue newValue)
    {
        this.newValue = newValue;
    }

    @Override
    public Object modify(Object value, float time)
    {
        return newValue.get();
    }

    @Override
    public void restart()
    {
    }
}
