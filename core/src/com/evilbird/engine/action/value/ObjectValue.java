package com.evilbird.engine.action.value;

public class ObjectValue implements ActionValue
{
    private Object value;

    public ObjectValue(Object value)
    {
        this.value = value;
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
