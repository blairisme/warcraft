/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame.variant;

import com.evilbird.warcraft.menu.ingame.IngameMenu;
import com.evilbird.warcraft.menu.ingame.IngameMenuStrings;

import static com.evilbird.warcraft.menu.ingame.IngameMenuDimensions.Small;
import static com.evilbird.warcraft.menu.outro.OutroMenuType.Defeat;

/**
 * Represents an in-game menu shown when the user has lost the game; they've
 * been defeated.
 *
 * @author Blair Butterworth
 */
public class DefeatMenu extends IngameMenu
{
    public DefeatMenu(IngameMenu menu, IngameMenuStrings strings) {
        super(menu);
        setLayout(Small);
        addTitle(strings.getDefeatTitle());
        addSpacer();
        addButton(strings.getOkButtonText(), () -> showMenu(Defeat));
    }
}
