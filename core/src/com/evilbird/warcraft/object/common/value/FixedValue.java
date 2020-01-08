/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.value;

import com.evilbird.warcraft.object.unit.Unit;
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

    private float value;

    public FixedValue(float value) {
        this.value = value;
    }

    @Override
    public float getValue(Unit unit) {
        return value;
    }

    public float getValue() {
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
