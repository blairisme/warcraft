/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.badlogic.gdx.Input;
import com.evilbird.warcraft.common.WarcraftPreferences;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.device.UserInputType.Key;
import static com.evilbird.warcraft.action.menu.MenuActions.VictoryMenu;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Standalone;

/**
 * Defines cheat user interactions.
 *
 * @author Blair Butterworth
 */
public class CheatInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public CheatInteractions(Provider<InteractionDefinition> factory, WarcraftPreferences preferences) {
        super(factory);
        victory(preferences);
    }

    private void victory(WarcraftPreferences preferences) {
        if (preferences.isDebugControlEnabled()) {
            addAction(VictoryMenu)
                .forInput(input -> input.getType() == Key && input.getKey() == Input.Keys.V)
                .assignedTo(item -> null)
                .appliedTo((t, s) -> null, (t, s) -> null)
                .appliedAs(Standalone);
        }
    }
}
