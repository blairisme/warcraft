/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Random;

/**
 * Instances of this class represent an {@link Identifier} whose underlying
 * value is a randomly generated number.
 *
 * @author Blair Butterworth
 */
public class RandomIdentifier implements Identifier
{
    private int value;

    public RandomIdentifier() {
        Random random = new Random();
        value = random.nextInt();
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        RandomIdentifier that = (RandomIdentifier)obj;
        return new EqualsBuilder()
            .append(value, that.value)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(value)
            .toHashCode();
    }
}
