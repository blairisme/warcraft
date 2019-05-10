/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    private int size;
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
        this.size = size;
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
        bits[x][y] = value;
    }

    /**
     * Sets the bits of the {@code BitMatrix} using the given string. The
     * string must contain only the '0' and '1' characters separated by the ','
     * character. Spaces are not allowed. Bits will be assigned on a row by row
     * basis. The given string does not need to be complete.
     *
     * <p>
     *     For example: '0,0,1,0,1,1,0,0,1'
     * </p>
     *
     * @param bits a non-empty string confirming to aforementioned pattern.
     */
    public void set(String bits) {
        Validate.notBlank(bits, "Bit string must not be blank");
        Validate.matchesPattern(bits, "[01,]*", "Only '0', '1' and ',' allowed in bit string");

        String[] components = bits.split(",");
        for (int i = 0; i < components.length; i++) {
            int x = i % size;
            int y = i == 0 ? 0 : i / size;
            boolean value = "1".equals(components[i]);
            set(x, y, value);
        }
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
            System.arraycopy(this.bits[x+i], y, result.bits[i], 0, size);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        BitMatrix matrix = (BitMatrix)obj;
        return new EqualsBuilder()
            .append(bits, matrix.bits)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(bits)
            .toHashCode();
    }

    @Override
    public String toString() {
        int toStringSize = (size*size)*2;
        StringBuilder builder = new StringBuilder(toStringSize);
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                builder.append(bits[x][y] ? "1," : "0,");
            }
        }
        builder.deleteCharAt(toStringSize-1);
        return builder.toString();
    }
}
