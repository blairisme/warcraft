package com.evilbird.engine.common.lang;

import java.util.UUID;

public class NamedIdentifier implements Identifier
{
    private String name;

    public NamedIdentifier()
    {
        this(UUID.randomUUID().toString());
    }

    public NamedIdentifier(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof NamedIdentifier){
            NamedIdentifier other = (NamedIdentifier)object;
            return Objects.equals(this.name, other.name);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    @Override
    public String toString()
    {
        return name;
    }
}
