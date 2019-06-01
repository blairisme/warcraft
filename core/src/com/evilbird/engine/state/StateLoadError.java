/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.state;

public class StateLoadError extends RuntimeException
{
    public StateLoadError(Throwable cause) {
        super(cause);
    }
}
