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
