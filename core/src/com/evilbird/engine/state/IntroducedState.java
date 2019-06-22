/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
