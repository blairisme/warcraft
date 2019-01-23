/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.badlogic.gdx.Screen;

/**
 * Implementors of this interface control which content is rendered to the
 * users viewport, their screen.
 *
 * @author Blair Butterworth
 */
public interface GameScreenManager
{
    void setScreen(Screen screen);
}
