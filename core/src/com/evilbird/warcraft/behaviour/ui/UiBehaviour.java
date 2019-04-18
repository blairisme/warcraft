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
import com.evilbird.warcraft.behaviour.ui.menu.MenuBehaviour;

import javax.inject.Inject;
import java.util.List;

public class UiBehaviour implements Behaviour
{
    private MenuBehaviour menuBehaviour;
    private InteractionBehaviour interactionBehaviour;

    @Inject
    public UiBehaviour(MenuBehaviour menuBehaviour, InteractionBehaviour interactionBehaviour) {
        this.menuBehaviour = menuBehaviour;
        this.interactionBehaviour = interactionBehaviour;
    }

    @Override
    public void update(State state, List<UserInput> input) {
        this.menuBehaviour.update(state, input);
        this.interactionBehaviour.update(state, input);
    }
}
