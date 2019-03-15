/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.engine.common.serialization.SerializedType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SerializedType("Named")
public class TextIdentifier implements Identifier
{
    private String name;

    @SerializedConstructor
    private TextIdentifier() {
    }

    public TextIdentifier(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

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
