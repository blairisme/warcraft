/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

public class SaveMenu extends IngameMenu
{
    public SaveMenu() {
        insertTitle("Save Game");
        insertTextField();
        insertList(null, null);
        insertButton("Save", this::showState);
    }
}
