/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.state;

import com.evilbird.engine.menu.MenuIdentifier;

/**
 * Represents a {@link State game state} that has an accompanying introduction,
 * explaining the context behind the state and any objectives required to
 * complete it.
 *
 * @author Blair Butterworth
 */
public interface IntroducedState
{
    MenuIdentifier getIntroductionMenu();
}
