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
 * Implementors of this interface represent a object which produces sound.
 * Methods are provided to obtain the currently playing sound and to and to
 * specify that another be used.
 *
 * @author Blair Butterworth
 */
public interface Audible
{
    /**
     * Returns the identifier of the currently playing sound effect.
     *
     * @return  an {@link Identifier}. This methods may return {@code null},
     *          indicating that no sound is currently being played.
     */
    Identifier getSound();

    /**
     * Returns whether or not the audible object supports the given sound.
     */
    boolean hasSound(Identifier id);

    /**
     * Sets the currently playing sound, identified by the given sound
     * identifier.
     *
     * @param id        an {@link Identifier}. This methods may be {@code null}
     *                  indicating that no sound should be used.
     * @param volume    the volume of the new sound.
     */
    void setSound(Identifier id, float volume);
}
