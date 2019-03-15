/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.state.StateType;

/**
 * Defines varieties of states, primarily built in states or user states.
 *
 * @author Blair Butterworth
 */
public enum WarcraftStateType implements StateType
{
    AssetState,
    UserState
}
