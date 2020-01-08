/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
     * Sets the Styleables skin, a bundle of named styles used to specify the
     * visual and audible presentation of the Styleable.
     *
     * @param skin a {@link Skin}.
     *
     * @throws NullPointerException thrown if the given {@code Skin} is
     *                              {@code null}.
     */
    void setSkin(Skin skin);

    /**
     * Sets the currently used style, thereby altering the visual and audible
     * presentation of the Styleable.
     *
     * @param name  a style name. This parameter cannot be {@code null} and
     *              must correspond to a style within the current skin.
     */
    void setStyle(String name);
}
