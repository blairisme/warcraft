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
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.behaviour.CompositeBehaviour;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourFactory;
import com.evilbird.warcraft.behaviour.ui.UiBehaviourFactory;
import com.evilbird.warcraft.behaviour.ui.objective.ObjectiveBehaviourFactory;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this class defines the game logic that modifies the state of
 * the game in response to user input, time and other factors.
 *
 * @author Blair Butterworth
 */
public class WarcraftBehaviourFactory implements BehaviourFactory
{
    private AiBehaviourFactory aiBehaviours;
    private UiBehaviourFactory uiBehaviours;
    private ObjectiveBehaviourFactory scenarioBehaviours;

    @Inject
    public WarcraftBehaviourFactory(
        AiBehaviourFactory aiBehaviours,
        UiBehaviourFactory uiBehaviours,
        ObjectiveBehaviourFactory scenarioBehaviours)
    {
        this.aiBehaviours = aiBehaviours;
        this.uiBehaviours = uiBehaviours;
        this.scenarioBehaviours = scenarioBehaviours;
    }

    @Override
    public void load(GameContext context) {
    }

    @Override
    public void unload(GameContext context) {
    }

    @Override
    public Behaviour get(Identifier identifier) {
        Validate.isInstanceOf(WarcraftBehaviour.class, identifier);
        WarcraftBehaviour type = (WarcraftBehaviour)identifier;

        BehaviourElement uiBehaviour = uiBehaviours.get();
        BehaviourElement aiBehaviour = aiBehaviours.get(type);
        BehaviourElement objectiveBehaviour = scenarioBehaviours.get(type);
        return new CompositeBehaviour(type, aiBehaviour, uiBehaviour, objectiveBehaviour);
    }
}
