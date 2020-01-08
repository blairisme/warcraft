/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.error;

/**
 * This exception is thrown when a method produces a result that exceeds
 * a size-related limit.
 *
 * @author Blair Butterworth
 */
public class SizeExceededException extends RuntimeException
{
    /**
     * Constructs a new instance of SizeExceededException using an explanation.
     * All other fields default to null.
     *
     * @param explanation Possibly null detail about this exception.
     */
    public SizeExceededException(String explanation) {
        super(explanation);
    }
}
