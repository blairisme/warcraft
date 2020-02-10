/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.lang;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * An {@link Identifier} comprised of two {@code Identifiers}.
 *
 * @author Blair Butterworth
 */
public class IdentifierPair implements Identifier
{
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
