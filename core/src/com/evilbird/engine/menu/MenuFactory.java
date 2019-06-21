/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.menu;

import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Persisted;

/**
 * Instances of this factory create {@link Menu} instances.
 *
 * @author Blair Butterworth
 */
public interface MenuFactory extends Persisted
{
    /**
     * Creates a new root menu, the home menu or "highest" level menu.
     *
     * @return a menu instance.
     */
    Menu newMenu();

    /**
     * Creates a new menu identified by the given identifier.
     *
     * @param identifier    the identifier of the menu to create.
     * @return              a menu instance.
     * @throws UnknownEntityException   thrown if a menu with the given
     *                                  identifier doesn't exist.
     */
    Menu newMenu(MenuIdentifier identifier) throws UnknownEntityException;
}
