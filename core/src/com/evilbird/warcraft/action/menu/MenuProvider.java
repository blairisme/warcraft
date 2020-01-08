/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.menu;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Implementors of this interface represent an object that can navigated
 * through.
 *
 * @author Blair Butterworth
 */
public interface MenuProvider
{
    void showMenu(Identifier location);
}
