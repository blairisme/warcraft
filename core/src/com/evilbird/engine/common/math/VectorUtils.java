/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.math;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Objects;

/**
 * Provides utility functions that operate on {@link Vector2} objects.
 *
 * @author Blair Butterworth
 */
public class VectorUtils
{
    private VectorUtils() {
    }

    /**
     * Returns a {@link Vector2} whose values are greater or equal to the given
     * minimum and less than or equal to the given maximum.
     *
     * @param value the value to clamp.
     * @param min   the minimum allowed value.
     * @param max   the maximum allowed value.
     * @return      a new {@link Vector2}, whose values lie between the given
     *              minimum and maximum values.
     *
     * @throws NullPointerException thrown if the given value, minimum or
     *                              maximum is {@code null}.
     */
    public static Vector2 clamp(Vector2 value, Vector2 min, Vector2 max) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(min);
        Objects.requireNonNull(max);

        Vector2 result = new Vector2();
        result.x = MathUtils.clamp(value.x, min.x, max.x);
        result.y = MathUtils.clamp(value.y, min.y, max.y);
        return result;
    }

    /**
     * Divides the given {@link Vector2} by the given factor.
     *
     * @param value     the value to divide.
     * @param factor    the factor to divide the value by.
     * @return          a new {@code Vector2}.
     *
     * @throws NullPointerException thrown if the given value is {@code null}.
     */
    public static Vector2 divide(Vector2 value, Vector2 factor) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(factor);

        Vector2 result = new Vector2();
        result.x = factor.x != 0 ? value.x / factor.x : 0;
        result.y = factor.y != 0 ? value.y / factor.y : 0;
        return result;
    }

    /**
     * Multiplies the given {@link Vector2} by the given factor.
     *
     * @param value     the value to multiply.
     * @param factor    the factor to multiply the value by.
     * @return          a new {@code Vector2}.
     *
     * @throws NullPointerException thrown if the given value is {@code null}.
     */
    public static Vector2 multiply(Vector2 value, float factor) {
        Objects.requireNonNull(value);
        Vector2 result = new Vector2(value);
        return result.scl(factor);
    }

    /**
     * Rounds the given {@link Vector2} to the nearest multiple.
     *
     * @param value     the value to round.
     * @param multiple  the nearest multiple.
     * @return          a new {@code Vector2}.
     *
     * @throws NullPointerException thrown if the given value or multiple is
     *                              {@code null}.
     */
    public static Vector2 round(Vector2 value, Vector2 multiple) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(multiple);

        Vector2 result = new Vector2(value);
        result.x = Math.round(result.x / multiple.x) * multiple.x;
        result.y = Math.round(result.y / multiple.y) * multiple.y;
        return result;
    }

    /**
     * Subtracts the given {@link Vector2} by the given delta.
     *
     * @param value     the value to multiply.
     * @param delta     the value to subtract.
     * @return          a new {@code Vector2}.
     *
     * @throws NullPointerException thrown if the given value or delta is
     *                              {@code null}.
     */
    public static Vector2 subtract(Vector2 value, Vector2 delta) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(delta);

        Vector2 result = new Vector2(value);
        return result.sub(delta);
    }
}
