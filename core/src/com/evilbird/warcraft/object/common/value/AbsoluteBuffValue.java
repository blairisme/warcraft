/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.value;

import com.evilbird.warcraft.object.unit.Unit;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A {@link Value} implementation representing a value that supplants another
 * value.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ValueSerializer.class)
public class AbsoluteBuffValue implements ModifiedValue
{
    private float absolute;
    private Value base;

    public AbsoluteBuffValue(float buff, Value base) {
        this.absolute = buff;
        this.base = base;
    }

    @Override
    public float getValue(Unit unit) {
        return absolute;
    }

    @Override
    public float getBaseValue(Unit unit) {
        if (base instanceof ModifiedValue) {
            ModifiedValue modified = (ModifiedValue)base;
            return modified.getBaseValue(unit);
        }
        return base.getValue(unit);
    }

    public Value getOriginal() {
        return base;
    }

    public float getModified() {
        return absolute;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        AbsoluteBuffValue that = (AbsoluteBuffValue)obj;
        return new EqualsBuilder()
            .append(base, that.base)
            .append(absolute, that.absolute)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(base)
            .append(absolute)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("absolute", absolute)
            .append("base", base)
            .toString();
    }
}
