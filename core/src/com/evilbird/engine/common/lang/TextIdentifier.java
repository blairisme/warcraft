/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.lang;

import com.evilbird.engine.common.serialization.SerializedConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Represents an {@link Identifier} based on a given text string, usually the
 * name of the object the identifier belongs to.
 *
 * @author Blair Butterworth
 */
public class TextIdentifier implements Identifier
{
    private String name;

    @SerializedConstructor
    private TextIdentifier() {
    }

    public TextIdentifier(String name) {
        this.name = name;
    }

    public static Identifier objectIdentifier(String prefix, Object object) {
        String unique = Integer.toHexString(System.identityHashCode(object));
        return new TextIdentifier(prefix + unique);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        TextIdentifier that = (TextIdentifier)obj;
        return new EqualsBuilder()
            .append(name, that.name)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(name)
            .toHashCode();
    }
}
