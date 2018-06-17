package com.evilbird.engine.common.lang;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Objects
{
    public static boolean equals(Object var0, Object var1) {
        return var0 == var1 || var0 != null && var0.equals(var1);
    }

    public static <T> T requireNonNull(T value, T alternative) {
        return value != null ? value : alternative;
    }
}
