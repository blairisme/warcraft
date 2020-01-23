/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.collection;

import com.evilbird.engine.common.text.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

/**
 * This class represents a matrix of bits, a NxN grid of bits. Each component
 * of the {@code BitMatrix} has a {@code boolean} value. The bits of a {@code
 * BitMatrix} are indexed by 2 dimensional coordinates. Individual bits can be
 * obtained, set, or cleared. By default, all bits in the {@code BitMatrix}
 * initially have the value {@code false}.
 *
 * @author Blair Butterworth
 */
public class BitMatrix
{
    private static final transient char TRUE_BIT = '1';
    private static final transient char FALSE_BIT = '0';

    private Object id;
    private int size;
    private int value;
    private String label;
    private boolean[][] bits;

    /**
     * Constructs a new instance of this class given its size: the number of
     * bits along the X and Y axis of the {@code BitMatrix}.
     *
     * @param size  the number of bits along the X and Y axis. This parameter
     *              must be a positive integer.
     */
    public BitMatrix(int size) {
        Validate.isTrue(size >= 0, "Size must be positive: %d", size);
        this.id = this;
        this.size = size;
        this.value = 0;
        this.label = StringUtils.newString(size * size, FALSE_BIT);
        this.bits = new boolean[size][size];
    }

    /**
     * Creates a new 3x3 {@code BitMatrix} populated with the given bits as
     * described in {@link BitMatrix#set(String)}.
     *
     * @param bits  a non-empty string describing the bits in the resulting
     *              {@code BitMatrix}.
     * @return      a new {@code BitMatrix}.
     */
    public static BitMatrix matrix3(String bits) {
        BitMatrix result = new BitMatrix(3);
        result.set(bits);
        return result;
    }

    /**
     * Creates a new 3x3 {@code BitMatrix} populated with the given bits as
     * described in {@link BitMatrix#set(String)}.
     *
     * @param bits  a non-empty string describing the bits in the resulting
     *              {@code BitMatrix}.
     * @param id    an identifier.
     *
     * @return      a new {@code BitMatrix}.
     */
    public static BitMatrix matrix3(String bits, Object id) {
        BitMatrix result = new BitMatrix(3);
        result.setId(id);
        result.set(bits);
        return result;
    }

    /**
     * Returns the bit at the given location.
     *
     * @param x the column of the desired bit. Must be a valid index
     *          (0 <= X < size).
     * @param y the row of the desired bit. Must be a valid index
     *          (0 <= Y < size).
     * @return  the desired bit.
     */
    public boolean get(int x, int y) {
        Validate.validIndex(bits, x, "Invalid array index (X): %d", x);
        Validate.validIndex(bits, y, "Invalid array index (Y): %d", y);
        return bits[x][y];
    }

    /**
     * Returns bit matrix's identifier if set, otherwise the object itself will
     * be returned.
     */
    public Object getId() {
       return id;
    }

    /**
     * Determines if this {@code BitMatrix} contains a single {@code true}
     * value.
     *
     * @return {@code true} if a single {@code true} bit is found.
     */
    public boolean isEmpty() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (this.bits[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sets the bit at the given location.
     *
     * @param x the column of the desired bit. Must be a valid index
     *          (0 <= X < size).
     * @param y the row of the desired bit. Must be a valid index
     *          (0 <= Y < size).
     * @param value the desired bit.
     */
    public void set(int x, int y, boolean value) {
        Validate.validIndex(bits, x, "Invalid array index (X): %d", x);
        Validate.validIndex(bits, y, "Invalid array index (Y): %d", y);
        this.bits[x][y] = value;
        this.label = StringUtils.setCharAt(label, (x * size) + y, value ? TRUE_BIT : FALSE_BIT);
        this.value = Integer.parseInt(label, 2);
    }

    /**
     * Sets the bits of the {@code BitMatrix} using the given string. The
     * string must contain only the '0' and '1' characters optionally separated
     * by the ',' character. Spaces are not allowed. Bits will be assigned on a
     * row by row basis. The given string does not need to be complete.
     *
     * <p>
     *     Example 1: '0,0,1,0,1,1,0,0,1'
     *     Example 2: '001011001'
     * </p>
     *
     * @param bits a non-empty string confirming to aforementioned pattern.
     */
    public void set(String bits) {
        Validate.notBlank(bits, "Bit string must not be blank");
        Validate.matchesPattern(bits, "[01,]*", "Only '0', '1' and ',' allowed in bit string");

        label = bits.replace(",", "");
        value = Integer.parseInt(label, 2);

        char[] components = label.toCharArray();
        for (int i = 0; i < components.length; i++) {
            int x = i % size;
            int y = i == 0 ? 0 : i / size;
            boolean value = TRUE_BIT == components[i];
            this.bits[x][y] = value;
        }
    }

    /**
     * Sets the identifier of the bit matrix. Identifiers are optional and do
     * not contribute towards the equality of the matrix.
     */
    public void setId(Object id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    /**
     * Returns a new {@code BitMatrix} of the specified size, containing the
     * bits of this {@code BitMatrix} starting from the given location.
     *
     * @param x     the column to create the sub matrix from. Must be a valid
     *              index (0 <= X < size).
     * @param y     the row to create the sub matrix from. Must be a valid
     *              index (0 <= Y < size).
     * @param size  the size of the new sub matrix.
     *
     * @return a new {@code BitMatrix}.
     */
    public BitMatrix subMatrix(int x, int y, int size) {
        Validate.validIndex(bits, x, "Invalid array index (X): %d", x);
        Validate.validIndex(bits, y, "Invalid array index (Y): %d", y);
        Validate.validIndex(bits, x + size - 1, "Sub matrix size too large (X): %d", size);
        Validate.validIndex(bits, y + size - 1, "Sub matrix size too large (Y): %d", size);

        BitMatrix result = new BitMatrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.set(j, i, get(i + x, j + y));
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) { return false; }
        if (object == this) { return true; }
        if (object.getClass() != getClass()) { return false; }

        BitMatrix matrix = (BitMatrix)object;
        return this.value == matrix.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return label;
    }
}
