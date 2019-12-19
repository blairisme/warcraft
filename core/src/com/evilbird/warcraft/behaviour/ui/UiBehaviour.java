/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui;

import com.evilbird.engine.behaviour.Behaviour;
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
public class UiBehaviour implements Behaviour
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
    public void update(State state, List<UserInput> input, float time) {
        this.menuBehaviour.update(state, input, time);
        this.mapBehaviour.update(state, input, time);
        this.interactionBehaviour.update(state, input, time);
    }
}
