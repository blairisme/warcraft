/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.Gdx;
import com.evilbird.warcraft.menu.common.ListMenu;
import com.evilbird.warcraft.menu.common.SelectListener;

public class HomeMenu extends ListMenu
{
    public HomeMenu() {
        addButton("Single Player Game", campaignMenu());
        addButton("Multi Player Game");
        addButton("Replay Introduction");
        addButton("Show Credits");
        addButton("Exit Program", exitProgram());
    }

    private SelectListener campaignMenu() {
        return () -> showMenu(MainMenu.Campaign);
    }

    private SelectListener exitProgram() {
        return () -> Gdx.app.exit();
    }
}
