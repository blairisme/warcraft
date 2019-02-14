/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.evilbird.warcraft.menu.common.events.SelectListener;

public class CampaignMenu extends MainMenu
{
    public CampaignMenu() {
        insertButton("New Map", showHumanLevel());
        insertButton("Load Game");
        insertButton("Custom Map");
        insertButton("Previous Menu", previousMenu());
    }

    private SelectListener showHumanLevel() {
        return () -> showMenu(MainMenuType.CampaignNew);
    }

    private SelectListener previousMenu() {
        return () -> showMenu(MainMenuType.Home);
    }
}
