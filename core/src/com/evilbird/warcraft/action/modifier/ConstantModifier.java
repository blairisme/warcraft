package com.evilbird.warcraft.action.modifier;

public class ConstantModifier implements ActionModifier
{
    private Object newValue;

    public ConstantModifier(Object newValue)
    {
        this.newValue = newValue;
    }

    @Override
    public Object modify(Object value, float time)
    {
        return newValue;
    }

    @Override
    public void restart()
    {
    }
}
