/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.ingame.variant;

import com.badlogic.gdx.Gdx;
import com.evilbird.warcraft.menu.ingame.IngameMenu;
import com.evilbird.warcraft.menu.ingame.IngameMenuStrings;

import static com.evilbird.warcraft.menu.ingame.IngameMenuDimensions.Normal;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Confirm;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Root;

/**
 * Represents an in-game menu shown when the user wishes to quit the game.
 *
 * @author Blair Butterworth
 */
public class QuitMenu extends IngameMenu
{
    public QuitMenu(IngameMenu menu, IngameMenuStrings strings) {
        super(menu);
        setLayout(Normal);
        addTitle(strings.getSurrenderTitle());
        addButton(strings.getRestartButtonText(), () -> showState(controller.getStateIdentifier()));
        addButton(strings.getSurrenderButtonText(), () -> showMenuOverlay(Confirm));
        addButton(strings.getExitButtonText(), () -> Gdx.app.exit());
        addSpacer();
        addButton(strings.getPreviousButtonText(), () -> showMenuOverlay(Root));
    }
}
