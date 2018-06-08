package com.evilbird.engine.common.function;

public class Suppliers
{
    public static Supplier<Boolean> isTrue() {
        return new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                return Boolean.TRUE;
            }
        };
    }
}
