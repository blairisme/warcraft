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
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Root;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Sounds;

/**
 * Represents an in-game menu that provides access to sounds, speeds and
 * preferences settings menus.
 *
 * @author Blair Butterworth
 */
public class OptionsMenu extends IngameMenu
{
    public OptionsMenu(IngameMenu menu, IngameMenuStrings strings) {
        super(menu);
        setLayout(Normal);
        addTitle(strings.getOptionsTitle());
        addButton(strings.getSoundsButtonText(), () -> showMenuOverlay(Sounds));
        addButton(strings.getSpeedsButtonText());
        addButton(strings.getPreferencesButtonText());
        addSpacer();
        addButton(strings.getPreviousButtonText(), () -> showMenuOverlay(Root));
    }
}
