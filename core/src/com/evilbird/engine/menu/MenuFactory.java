/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
}
