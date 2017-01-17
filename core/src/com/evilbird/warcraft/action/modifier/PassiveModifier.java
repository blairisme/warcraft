package com.evilbird.warcraft.action.modifier;

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
