/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.evilbird.engine.state.StateIdentifier;

/**
 * Identifies a game state created when the game engine is paused.
 *
 * @author Blair Butterworth
 */
public enum GameState implements StateIdentifier
{
    AutoSave
}
