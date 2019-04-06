/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.common;

import com.badlogic.gdx.math.GridPoint2;
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
//TODO: Replace 2d array with BitSet
public class BitMatrix
{
    private int size;
    private boolean[][] values;

    public BitMatrix(int size) {
        this.size = size;
        this.values = new boolean[size][size];
    }

    public static BitMatrix matrix(int size, String bits) {
        BitMatrix result = new BitMatrix(size);
        result.set(bits);
        return result;
    }

    public static BitMatrix matrix3(String bits) {
        return matrix(3, bits);
    }

    public boolean isEmpty() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (this.values[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean get(int x, int y) {
        return values[x][y];
    }

    public boolean get(GridPoint2 point) {
        return values[point.x][point.y];
    }

    public BitMatrix getSubMatrix(int xIndex, int yIndex, int size) {
        BitMatrix result = new BitMatrix(size);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                result.values[x][y] = this.values[x + xIndex][y + yIndex];
            }
        }
        return result;
    }

    public void set(int x, int y) {
        values[x][y] = true;
    }

    public void set(int x, int y, boolean value) {
        values[x][y] = value;
    }

    public void set(GridPoint2 point) {
        values[point.x][point.y] = true;
    }

    public void set(GridPoint2 ... points) {
        for (GridPoint2 point: points) {
            set(point);
        }
    }

    public void set(String bits) {
        Validate.matchesPattern(bits, "[01,]*", "Only '0', '1' and ',' allowed in bit string");
        String[] components = bits.split(",");
        for (int i = 0; i < components.length; i++) {
            int x = i % size;
            int y = i == 0 ? 0 : i / size;
            boolean value = "1".equals(components[i]);
            set(x, y, value);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        BitMatrix matrix = (BitMatrix)obj;
        return new EqualsBuilder()
            .append(size, matrix.size)
            .append(values, matrix.values)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(size)
            .append(values)
            .toHashCode();
    }
}
