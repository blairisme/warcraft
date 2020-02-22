/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.state.State;
import org.apache.commons.lang3.Validate;

import java.util.Arrays;
import java.util.List;

/**
 * Instances of this {@link Behaviour} use the Composite pattern to make a
 * collection of behaviours appear as a single behaviour.
 *
 * @author Blair Butterworth
 */
public class CompositeBehaviour implements Behaviour
{
    private transient BehaviourIdentifier identifier;
    private transient List<BehaviourElement> behaviours;

    public CompositeBehaviour(BehaviourIdentifier identifier, BehaviourElement ... behaviours) {
        Validate.notNull(behaviours);
        this.identifier = identifier;
        this.behaviours = Arrays.asList(behaviours);
    }

    @Override
    public BehaviourIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public void apply(State state, List<UserInput> input, float time) {
        for (BehaviourElement behaviour : behaviours) {
            behaviour.apply(state, input, time);
        }
    }
}
