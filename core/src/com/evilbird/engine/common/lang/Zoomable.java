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
 * Implementors of this interface represent an object whose presentation can be
 * magnified. Methods are provided to set and retrieve the magnification applied
 * to the object.
 *
 * @author Blair Butterworth
 */
public interface Zoomable
{
    float getZoom();

    float getOriginalZoom();

    void setZoom(float zoom);

    void setOriginalZoom(float originalZoom);
}
