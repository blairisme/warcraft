/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import static com.evilbird.warcraft.menu.ingame.IngameMenus.*;
import static com.evilbird.warcraft.menu.ingame.IngameMenus.Objectives;

public class RootMenu extends IngameMenu
{
    public RootMenu() {
        insertTitle("Game Menu");
        insertButton("Save", Save);
        insertButton("Load", Load);
        insertButton("Options", Options);
        insertButton("Scenario Objectives", Objectives);
        insertButton("End Scenario", this::showMenu);
        insertSpacer();
        insertButton("Return to Game", this::showState);
    }
}
