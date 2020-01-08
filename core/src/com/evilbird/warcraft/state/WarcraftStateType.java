/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
