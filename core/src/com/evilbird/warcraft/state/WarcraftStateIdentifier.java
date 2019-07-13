/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.state.StateIdentifier;

/**
 * Represents a unique identifier for a Warcraft game state, a persisted set of
 * game objects and their properties at a given point in time.
 *
 * @author Blair Butterworth
 */
public interface WarcraftStateIdentifier extends StateIdentifier
{
    String getFilePath();
}
