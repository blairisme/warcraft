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

import static com.evilbird.warcraft.menu.ingame.IngameMenuDimensions.Normal;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Exit;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Load;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Objectives;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Options;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Save;

/**
 * Represents the highest level in-game menu, from which other in-game menus
 * can be accessed.
 *
 * @author Blair Butterworth
 */
public class RootMenu extends IngameMenu
{
    public RootMenu(IngameMenu menu, IngameMenuStrings strings) {
        super(menu);
        setLayout(Normal);
        addTitle(strings.getMainTitle());
        addButton(strings.getSaveButtonText(), () -> showMenuOverlay(Save));
        addButton(strings.getLoadButtonText(), () -> showMenuOverlay(Load));
        addButton(strings.getOptionsButtonText(), () -> showMenuOverlay(Options));
        addButton(strings.getObjectivesButtonText(), () -> showMenuOverlay(Objectives));
        addButton(strings.getEndButtonText(), () -> showMenuOverlay(Exit));
        addSpacer();
        addButton(strings.getReturnButtonText(), () -> showState());
    }
}
