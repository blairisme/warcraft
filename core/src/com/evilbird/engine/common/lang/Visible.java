/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

/**
 * Implementors of this interface represent an object with visibility. Usually
 * this relates to object rendering.
 *
 * @author Blair Butterworth
 */
public interface Visible
{
    boolean getVisible();

    void setVisible(boolean visible);
}
