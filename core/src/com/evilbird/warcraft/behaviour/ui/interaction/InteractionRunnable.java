/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

public class InteractionRunnable implements Runnable
{
    private Item target;
    private Item selected;
    private UserInput input;
    private Interaction interaction;

    public InteractionRunnable(Interaction interaction, UserInput input, Item target, Item selected) {
        this.target = target;
        this.selected = selected;
        this.input = input;
        this.interaction = interaction;
    }

    @Override
    public void run() {
        interaction.update(input, target, selected);
    }
}
