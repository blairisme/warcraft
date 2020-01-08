/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

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
