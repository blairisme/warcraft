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
 * An annotation that indicates this class will be serialized to JSON with
 * the provided name value as its field name.
 *
 * @author Blair Butterworth
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface SerializedType
{
    String value();
}
