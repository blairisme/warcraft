/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.behaviour;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.behaviour.BehaviourIdentifier;

import javax.inject.Inject;

/**
 * @author Blair Butterworth
 */
public class BehaviourStateFactory
{
    private BehaviourFactory behaviourFactory;

    @Inject
    public BehaviourStateFactory(BehaviourFactory behaviourFactory) {
        this.behaviourFactory = behaviourFactory;
    }

    public Behaviour get(BehaviourStateIdentifier identifier) {
        return behaviourFactory.newBehaviour(null); //TODO
    }
}
