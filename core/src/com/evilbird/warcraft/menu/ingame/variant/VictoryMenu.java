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
import static com.evilbird.warcraft.menu.outro.OutroMenuType.Victory;

/**
 * Represents an in-game menu shown when the user has completed the objectives
 * of the current game; they're victorious.
 *
 * @author Blair Butterworth
 */
public class VictoryMenu extends IngameMenu
{
    public VictoryMenu(IngameMenu menu, IngameMenuStrings strings) {
        super(menu);
        setLayout(Small);
        addTitle(strings.getVictoryTitle());
        addSpacer();
        addButton(strings.getOkButtonText(), () -> showMenu(Victory));
    }
}
