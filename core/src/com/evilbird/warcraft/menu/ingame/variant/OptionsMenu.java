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
