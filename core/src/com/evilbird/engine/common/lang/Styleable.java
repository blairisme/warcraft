/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Implementors of this interface represent a game object whose visual and
 * audible representation can be specified via a style contained within a
 * {@link Skin}.
 *
 * @author Blair Butterworth
 */
public interface Styleable
{
    /**
     * Returns the Styleables skin, a bundle of named styles used to specify
     * the visual and audible presentation of the Styleable.
     *
     * @return a {@link Skin}. This method will not return {@code null}.
     */
    Skin getSkin();

    /**
     * Sets the currently used style, thereby altering the visual and audible
     * presentation of the Styleable.
     *
     * @param name  a style name. This parameter cannot be {@code null} and
     *              must correspond to a style within the current skin.
     */
    void setStyle(String name);
}
