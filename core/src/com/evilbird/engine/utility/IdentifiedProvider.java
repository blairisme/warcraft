package com.evilbird.engine.utility;

public interface IdentifiedProvider<T>
{
    T get(Identifier identifier);
}
