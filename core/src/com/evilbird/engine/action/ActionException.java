/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Thrown if an error occurs during Action execution.
 *
 * @author Blair Butterworth
 */
public class ActionException extends RuntimeException
{
    public ActionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append(getMessage())
            .build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        ActionException that = (ActionException)obj;
        return new EqualsBuilder()
            .append(getMessage(), that.getMessage())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(getMessage())
            .toHashCode();
    }
}
