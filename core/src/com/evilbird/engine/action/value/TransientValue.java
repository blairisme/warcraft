package com.evilbird.engine.action.value;

public class TransientValue implements ActionValue
{
    private Object value;

    public TransientValue()
    {
        value = null;
    }

    @Override
    public Object get()
    {
        return value;
    }

    @Override
    public void set(Object value)
    {
        this.value = value;
    }
}
