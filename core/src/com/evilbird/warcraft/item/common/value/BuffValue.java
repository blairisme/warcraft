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
 * A {@link Value} implementation representing a value that supplements another
 * value, modifying it by a set amount.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ValueSerializer.class)
public class BuffValue implements ModifiedValue
{
    private int buff;
    private Value base;

    public BuffValue(int buff, Value base) {
        this.buff = buff;
        this.base = base;
    }

    @Override
    public int getValue(Unit unit) {
        return base.getValue(unit) + buff;
    }

    @Override
    public int getBaseValue(Unit unit) {
        if (base instanceof ModifiedValue) {
            ModifiedValue modified = (ModifiedValue)base;
            return modified.getBaseValue(unit);
        }
        return base.getValue(unit);
    }

    public Value getOriginal() {
        return base;
    }

    public int getModifier() {
        return buff;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        BuffValue that = (BuffValue)obj;
        return new EqualsBuilder()
            .append(base, that.base)
            .append(buff, that.buff)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(base)
            .append(buff)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("buff", buff)
            .append("base", base)
            .toString();
    }
}
