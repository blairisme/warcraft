/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourElement;
import com.evilbird.engine.behaviour.BehaviourIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.state.State;
import com.google.gson.annotations.JsonAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Instances of this {@link Behaviour} use the Composite pattern to make a
 * collection of behaviours appear as a single behaviour.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(WarcraftBehaviourSerializer.class)
public class WarcraftBehaviour implements Behaviour
{
    private transient BehaviourIdentifier identifier;
    private transient List<BehaviourElement> behaviours;

    public WarcraftBehaviour(BehaviourIdentifier identifier, BehaviourElement ... behaviours) {
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
