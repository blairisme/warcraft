package com.evilbird.warcraft.action.modifier;

import com.evilbird.warcraft.action.value.ActionValue;
import com.evilbird.warcraft.action.value.ObjectValue;

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
