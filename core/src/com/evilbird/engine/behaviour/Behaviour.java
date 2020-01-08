/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour;

import com.evilbird.engine.common.lang.GenericIdentifier;
import com.evilbird.engine.common.lang.Identifiable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.state.State;

import java.util.List;

/**
 * Implementors of this interface define game logic that decides which
 * modifications to the game state should be made in response to user input,
 * time and other factors.
 *
 * @author Blair Butterworth
 */
public interface Behaviour extends Identifiable
{
    /**
     * Provides the behaviour the opportunity to apply the game state. The
     * behaviour is also provided with any user input that occurred between
     * this and the previous call to apply .
     *
     * @param state the current game state.
     * @param input a list of user input in the order in which they occurred.
     * @param time  the difference in time between this call to update and the
     *              last call to update.
     */
    void update(State state, List<UserInput> input, float time);

    default Identifier getIdentifier() {
        return GenericIdentifier.Unknown;
    }
}
