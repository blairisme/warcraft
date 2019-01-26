/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Instances of this class modify the game state on behalf of the non-human
 * players.
 *
 * @author Blair Butterworth
 */
public class AiBehaviour implements Behaviour
{
    private Collection<AiProcedure> procedures;

    @Inject
    public AiBehaviour(InitiateAttack initiateAttack) {
        procedures = new ArrayList<>();
        //procedures.add(initiateAttack);
    }

    @Override
    public void update(ItemRoot world, ItemRoot hud, List<UserInput> input) {
        for (AiProcedure procedure: procedures) {
            procedure.update(world);
        }
    }
}
