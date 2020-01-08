/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.menu;

import com.evilbird.engine.game.GameFactory;

/**
 * Instances of this factory create {@link Menu} instances.
 *
 * @author Blair Butterworth
 */
public interface MenuFactory extends GameFactory<Menu>
{
    /**
     * Creates a new root menu, the home menu or "highest" level menu.
     *
     * @return a menu instance.
     */
    Menu get();

    /**
     * Loads the assets required to display the root menu, the home menu or
     * "highest" level menu.
     */
    void load();
}
