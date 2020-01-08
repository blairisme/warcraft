/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.state;

/**
 * Implementors of this interface represent a sequence of states. Methods are
 * provided to return the identifier of the state to be shown when the current
 * state is complete.
 *
 * @author Blair Butterworth
 */
public interface StateSequence
{
    StateIdentifier getNextState();
}
