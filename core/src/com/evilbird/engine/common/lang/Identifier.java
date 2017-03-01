package com.evilbird.engine.common.lang;

import java.util.UUID;

//TODO: Remove, replacing with IdentifierNew
@Deprecated
public class Identifier
{
    private int id;
    private String name;

    public Identifier()
    {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.hashCode();
        this.name = uuid.toString();
    }

    public Identifier(Identifier identifier)
    {
        this.id = identifier.id;
        this.name = identifier.name;
    }

    public Identifier(String identifier)
    {
        this.id = identifier.hashCode();
        this.name = identifier;
    }

    public Identifier(int identifier)
    {
        this.id = identifier;
        this.name = Integer.toString(identifier);
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (object instanceof Identifier)
        {
            Identifier other = (Identifier)object;
            if (this.id != other.id) return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
