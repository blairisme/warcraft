/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
