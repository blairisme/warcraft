/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

/**
 * A peer of the Java Objects class, which isn't available on certain versions
 * of Android.
 *
 * @author Blair Butterworth
 */
//TODO: Replace with java objects now sdk has been bumped
public class Objects
{
    public static boolean equals(Object var0, Object var1) {
        return var0 == var1 || var0 != null && var0.equals(var1);
    }

    public static <T> T requireNonNull(T value, T alternative) {
        return value != null ? value : alternative;
    }
}
