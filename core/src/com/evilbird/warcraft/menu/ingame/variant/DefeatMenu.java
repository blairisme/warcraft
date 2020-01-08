/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
