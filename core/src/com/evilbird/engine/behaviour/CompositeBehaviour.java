/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.behaviour;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.state.State;
import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 * Instances of this {@link Behaviour} use the Composite pattern to make a
 * collection of behaviours appear as a single behaviour.
 *
 * @author Blair Butterworth
 */
public class CompositeBehaviour implements Behaviour
{
    private List<Behaviour> behaviours;

    public CompositeBehaviour(List<Behaviour> behaviours) {
        Validate.notNull(behaviours);
        this.behaviours = behaviours;
    }

    @Override
    public Identifier getIdentifier() {
        return null;
    }

    @Override
    public void update(State state, List<UserInput> input) {
        for (Behaviour behaviour : behaviours) {
            behaviour.update(state, input);
        }
    }
}
