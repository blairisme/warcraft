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
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Defeat;

/**
 * Represents an in-game menu that confirms whether the user wishes to quit the
 * game.
 *
 * @author Blair Butterworth
 */
public class SurrenderMenu extends IngameMenu
{
    public SurrenderMenu(IngameMenu menu, IngameMenuStrings strings) {
        super(menu);
        setLayout(Normal);
        addTitle(strings.getSurrenderTitle());
        addButton(strings.getSurrenderButtonText(), () -> showMenuOverlay(Defeat));
        addSpacer();
        addButton(strings.getCancelButtonText(), this::showState);
    }
}
