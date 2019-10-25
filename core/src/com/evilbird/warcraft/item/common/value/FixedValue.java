/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.value;

import com.evilbird.warcraft.item.unit.Unit;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A {@link Value} implementation representing a fixed, static value.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ValueSerializer.class)
public class FixedValue implements Value
{
    public static final Value Zero = new FixedValue(0);

    private int value;

    public FixedValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue(Unit unit) {
        return value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        FixedValue that = (FixedValue)obj;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("value", value)
            .toString();
    }
}
