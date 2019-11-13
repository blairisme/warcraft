/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.device.UserInputType.Menu;
import static com.evilbird.warcraft.action.menu.MenuActions.ActionsMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.BuildAdvancedMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.BuildSimpleMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.IngameMenu;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Target;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Standalone;
import static com.evilbird.warcraft.object.display.HudControl.MenuPane;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.BuildSimpleButton;

/**
 * Defines user interactions that result in manipulation of the menu user
 * interface.
 *
 * @author Blair Butterworth
 */
public class MenuInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public MenuInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        buildMenuInteractions();
        ingameMenuInteractions();
    }

    private void buildMenuInteractions() {
        addAction(ActionsMenu)
            .whenTarget(BuildCancelButton)
            .appliedTo(Target)
            .appliedAs(Addition);

        addAction(BuildSimpleMenu)
            .whenTarget(BuildSimpleButton)
            .appliedTo(Target)
            .appliedAs(Addition);

        addAction(BuildAdvancedMenu)
            .whenTarget(BuildAdvancedButton)
            .appliedTo(Target)
            .appliedAs(Addition);
    }

    private void ingameMenuInteractions() {
        addAction(IngameMenu)
            .whenTarget(MenuPane)
            .appliedTo(Target)
            .appliedAs(Addition);

        addAction(IngameMenu)
            .forInput(Menu)
            .assignedTo(item -> null)
            .appliedTo((t, s) -> null, (t, s) -> null)
            .appliedAs(Standalone);
    }
}
