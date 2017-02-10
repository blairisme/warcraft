package com.evilbird.engine.action.modifier;

public class PassiveModifier implements ActionModifier
{
    @Override
    public Object modify(Object value, float time)
    {
        return value;
    }

    @Override
    public void restart()
    {
    }
}
