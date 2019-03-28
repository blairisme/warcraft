/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
