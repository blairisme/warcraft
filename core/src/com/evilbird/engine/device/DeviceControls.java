/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.device;

/**
 * Implementors of this interface provide information about the controller used
 * to provide input to the game.
 *
 * @author Blair Butterworth
 */
public interface DeviceControls
{
    boolean hasMenuButton();
}
