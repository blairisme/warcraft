/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a constructor declaration is intended for use in
 * serialization.
 *
 * @author Blair Butterworth
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.SOURCE)
public @interface SerializedConstructor {
}
