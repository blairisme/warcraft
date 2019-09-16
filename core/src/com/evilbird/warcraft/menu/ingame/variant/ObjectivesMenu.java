/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame.variant;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.IntroducedState;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.menu.ingame.IngameMenu;
import com.evilbird.warcraft.menu.ingame.IngameMenuStrings;
import com.evilbird.warcraft.menu.intro.IntroMenuType;

import static com.evilbird.warcraft.menu.ingame.IngameMenuDimensions.Normal;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Root;

/**
 * Represents a user interface element that displays the objectives of the
 * current scenario.
 *
 * @author Blair Butterworth
 */
public class ObjectivesMenu extends IngameMenu
{
    private Label title;
    private Label objectives;
    private IngameMenuStrings strings;

    public ObjectivesMenu(IngameMenu menu, IngameMenuStrings strings) {
        super(menu);
        this.strings = strings;
        setLayout(Normal);
        addLabels(strings);
        addButtons(strings);
    }

    private void addLabels(IngameMenuStrings strings) {
        title = addTitle(strings.getObjectivesTitle());
        objectives = addLabel(strings.getObjectivesDefault());
    }

    private void addButtons(IngameMenuStrings strings) {
        addSpacer();
        addButton(strings.getPreviousButtonText(), () -> showMenuOverlay(Root));
    }

    @Override
    public void setController(GameController controller) {
        super.setController(controller);
        setObjectives(controller.getStateIdentifier());
    }

    protected void setObjectives(StateIdentifier identifier) {
        if (identifier instanceof IntroducedState) {
            IntroducedState introducedState = (IntroducedState)identifier;
            MenuIdentifier introductionMenu = introducedState.getIntroductionMenu();

            if (introductionMenu instanceof IntroMenuType) {
                IntroMenuType introMenu = (IntroMenuType)introductionMenu;
                objectives.setText(strings.getObjectives(introMenu.getIndex()));
            }
        }
    }
}
