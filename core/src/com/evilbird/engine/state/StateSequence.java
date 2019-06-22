/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
