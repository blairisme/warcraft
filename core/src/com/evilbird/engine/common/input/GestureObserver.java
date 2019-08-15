/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.input.GestureDetector.GestureListener;

/**
 * Implementors of this interface provide methods receive gesture events.
 *
 * @author Blair Butterworth
 */
public interface GestureObserver extends GestureListener
{
    /**
     * Called when a key is pressed.
     *
     * @param keycode a code representing the key that has been pressed.
     *
     * @return  {@code true} if the event has been processed, or {@code false}
     *          if other listeners should continue to process the event.
     *
     * @see Input.Keys
     */
    boolean keyDown(int keycode);

    /**
     * Called when a pressed key is released.
     *
     * @param keycode a code representing the key that was pressed.
     *
     * @return  {@code true} if the event has been processed, or {@code false}
     *          if other listeners should continue to process the event.
     *
     * @see Input.Keys
     */
    boolean keyUp(int keycode);

    /**
     * Called when a previously pressed finger or a mouse button is released.
     *
     * @param screenX   the x-axis coordinate of the input.
     * @param screenY   the y-axis coordinate of the input.
     * @param pointer   the pointer for the event.
     * @param button    the button that was released. On MacOS this will be
     *                  {@link Input.Buttons#LEFT}.
     *
     * @return  {@code true} if the event has been processed, or {@code false}
     *          if other listeners should continue to process the event.
     *
     * @see Input.Buttons
     */
    boolean touchUp(int screenX, int screenY, int pointer, int button);

    /**
     * Called when the mouse wheel is scrolled.
     *
     * @apiNote Will not be called on iOS.
     *
     * @param amount the scroll amount, -1 or 1 depending on the direction the
     *               wheel was scrolled.
     *
     * @return  {@code true} if the event has been processed, or {@code false}
     *          if other listeners should continue to process the event.
     */
    boolean scrolled(int amount);
}
