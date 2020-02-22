/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui;

import com.evilbird.engine.behaviour.BehaviourElement;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.behaviour.ui.interaction.InteractionBehaviour;
import com.evilbird.warcraft.behaviour.ui.menu.MapBehaviour;
import com.evilbird.warcraft.behaviour.ui.menu.MenuBehaviour;

import javax.inject.Inject;
import java.util.List;

/**
 * Instances of this class modify the game state in response to user
 * interaction and updates to the game state that should be presented to the
 * user. I.E., the users current game resources.
 *
 * @author Blair Butterworth
 */
public class UiBehaviour implements BehaviourElement
{
    private InteractionBehaviour interactionBehaviour;
    private MapBehaviour mapBehaviour;
    private MenuBehaviour menuBehaviour;

    @Inject
    public UiBehaviour(
        InteractionBehaviour interactionBehaviour,
        MapBehaviour mapBehaviour,
        MenuBehaviour menuBehaviour)
    {
        this.menuBehaviour = menuBehaviour;
        this.mapBehaviour = mapBehaviour;
        this.interactionBehaviour = interactionBehaviour;
    }

    @Override
    public void apply(State state, List<UserInput> input, float time) {
        this.menuBehaviour.apply(state, input, time);
        this.mapBehaviour.apply(state, input, time);
        this.interactionBehaviour.apply(state, input, time);
    }
}
