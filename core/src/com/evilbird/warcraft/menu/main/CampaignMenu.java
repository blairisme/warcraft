/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.evilbird.warcraft.menu.common.ListMenu;
import com.evilbird.warcraft.menu.common.SelectListener;

public class CampaignMenu extends ListMenu
{
    public CampaignMenu() {
        addButton("New Campaign", showHumanLevel());
        addButton("Load Game");
        addButton("Custom Scenario");
        addButton("Previous Menu", previousMenu());
    }

    private SelectListener showHumanLevel() {
        return () -> showMenu(MainMenu.CampaignNew);
    }

    private SelectListener previousMenu() {
        return () -> showMenu(MainMenu.Home);
    }
}
