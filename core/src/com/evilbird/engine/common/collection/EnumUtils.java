/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import org.apache.commons.lang3.StringUtils;

/**
 * Provides utility functions for working with enumerations.
 *
 * @author Blair Butterworth
 */
public class EnumUtils
{
    private EnumUtils() {
    }

    /**
     * Returns the name of the given enum constant, minus the an optional
     * prefix and suffix.
     *
     * @param value     the enum whose name will be returned.
     * @param prefix    an optional prefix to remove from the resulting value.
     * @param suffix    an optional suffix to remove from the resulting value.
     * @return          the name of the given enum constant.
     */
    public static String getName(Enum<?> value, String prefix, String suffix) {
        String result = value.name();
        result = StringUtils.removeStart(result, prefix);
        result = StringUtils.removeEnd(result, suffix);
        return result;
    }

    /**
     * Determines whether the given enum constant is declared between the given
     * enum constants (inclusive).
     *
     * @param subject   an enum constant.
     * @param first     the start of the enum constant range under test.
     * @param last      the end of the enum constant range under test.
     * @param <T>       the enum constant type.
     * @return          {@code true} if the enum constant is within the
     *                  constant range.
     */
    public static <T extends Enum<T>> boolean isBetween(T subject, T first, T last) {
        return subject.ordinal() >= first.ordinal() && subject.ordinal() <= last.ordinal();
    }

    public static <T extends Enum<T>> boolean isAny(T subject, T ... values) {
        for (T value: values) {
            if (subject == value) {
                return true;
            }
        }
        return false;
    }
}
