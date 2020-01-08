/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.lang;

/**
 * Implementors of this interface represent a visual object whose visual
 * representation is constantly updated. Methods are provided to obtain the
 * currently displayed animation and to specify that another be used.
 *
 * @author Blair Butterworth
 */
public interface Animated
{
    /**
     * Returns the identifier of the currently displayed animation.
     *
     * @return  an {@link Identifier}. This methods may return {@code null},
     *          indicating that no animation is current used.
     */
    Identifier getAnimation();

    /**
     * Returns whether or not the animated object supports the given animation.
     */
    boolean hasAnimation(Identifier id);

    /**
     * Sets the currently displayed animation, identified by the given
     * animation identifier.
     *
     * @param id    an {@link Identifier}.
     *
     * @throws NullPointerException if the given identifier is {@code null}.
     */
    void setAnimation(Identifier id);

    /**
     * Sets the currently displayed animation, identified by the given
     * animation identifier, and instructs it to start rendering from the given
     * time.
     *
     * @param id    an {@link Identifier}.
     * @param time  the time at which to start the animation.
     *
     * @throws NullPointerException if the given identifier is {@code null}.
     */
    void setAnimation(Identifier id, float time);

    /**
     * Allows an animation to be referred to using an alias.
     *
     * @param animationId   the animation to be assigned an alias.
     * @param aliasId       an alternative identifier for the given animation.
     *
     * @throws NullPointerException if either of the given identifiers is
     *                              {@code null}.
     */
    void setAnimationAlias(Identifier animationId, Identifier aliasId);
}
