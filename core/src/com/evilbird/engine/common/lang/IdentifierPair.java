/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * An {@link Identifier} comprised of two {@code Identifiers}.
 *
 * @author Blair Butterworth
 */
public class IdentifierPair implements Identifier {
    private Identifier group;
    private Identifier value;

    public IdentifierPair(Identifier group, Identifier value) {
        this.group = group;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        IdentifierPair that = (IdentifierPair)obj;
        return new EqualsBuilder()
            .append(group, that.group)
            .append(value, that.value)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(group)
            .append(value)
            .toHashCode();
    }
}
