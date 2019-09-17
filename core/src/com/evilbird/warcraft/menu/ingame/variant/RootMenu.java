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
