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
import com.evilbird.warcraft.menu.common.events.SelectListener;
import com.evilbird.warcraft.menu.intro.IntroMenu;

public class NewCampaignMenu extends ListMenu
{
    public NewCampaignMenu() {
        insertButton("Human Campaign", showHumanLevel());
        insertButton("Orc Campaign");
        insertButton("Previous Menu", previousMenu());
    }

    private SelectListener showHumanLevel() {
        return () -> showMenu(IntroMenu.HumanLevel1);
    }

    private SelectListener previousMenu() {
        return () -> showMenu(MainMenu.Campaign);
    }
}
