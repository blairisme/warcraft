/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.menu;

/**
 * Instances of this factory create {@link Menu} instances.
 *
 * @author Blair Butterworth
 */
public interface MenuFactory
{
    /**
     * Loads an assets required by the MenuFactory.
     */
    void load();

    /**
     * Creates a new root menu, the home menu or "highest" level menu.
     *
     * @return a menu instance.
     */
    Menu newMenu();

    /**
     * Creates a new menu identified by the given identifier.
     *
     * @param identifier the identifier of the menu to create.
     *
     * @return a menu instance.
     */
    Menu newMenu(MenuIdentifier identifier);
}
