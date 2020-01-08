/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Defines options for specifying AI behaviours.
 *
 * @author Blair Butterworth
 */
public enum AiBehaviours implements Identifier
{
    HumanEasy,
    HumanMedium,
    HumanHard,

    OrcEasy,
    OrcMedium,
    OrcHard
}
