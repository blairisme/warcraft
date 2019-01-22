/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.behaviour;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.ItemRoot;

import java.util.List;

public class CompositeBehaviour implements Behaviour
{
    private List<Behaviour> behaviours;

    public CompositeBehaviour(List<Behaviour> behaviours) {
        this.behaviours = behaviours;
    }

    @Override
    public void update(ItemRoot world, ItemRoot hud, List<UserInput> input) {
        for (Behaviour behaviour : behaviours) {
            behaviour.update(world, hud, input);
        }
    }
}
