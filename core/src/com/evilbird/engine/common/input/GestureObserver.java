/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.input;

import com.badlogic.gdx.input.GestureDetector.GestureListener;

/**
 * Implementors of this interface provide methods receive gesture events.
 *
 * @author Blair Butterworth
 */
public interface GestureObserver extends GestureListener
{
    boolean touchUp(int screenX, int screenY, int pointer, int button);

    boolean scrolled(int amount);
}
