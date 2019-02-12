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
import com.evilbird.warcraft.menu.common.events.SelectListener;

public class HomeMenu extends MainMenu
{
    public HomeMenu() {
        insertButton("Single Player Game", campaignMenu());
        insertButton("Multi Player Game");
        insertButton("Replay Introduction");
        insertButton("Show Credits");
        insertButton("Exit Program", exitProgram());
    }

    private SelectListener campaignMenu() {
        return () -> showMenu(MainMenuType.Campaign);
    }

    private SelectListener exitProgram() {
        return () -> Gdx.app.exit();
    }
}
