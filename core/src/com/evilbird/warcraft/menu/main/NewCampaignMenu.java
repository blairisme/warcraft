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
import com.evilbird.warcraft.menu.intro.IntroMenuType;

public class NewCampaignMenu extends MainMenu
{
    public NewCampaignMenu() {
        insertButton("Human Campaign", showHumanLevel());
        insertButton("Orc Campaign");
        insertButton("Previous Menu", previousMenu());
    }

    private SelectListener showHumanLevel() {
        return () -> showMenu(IntroMenuType.HumanLevel1);
    }

    private SelectListener previousMenu() {
        return () -> showMenu(MainMenuType.Campaign);
    }
}
