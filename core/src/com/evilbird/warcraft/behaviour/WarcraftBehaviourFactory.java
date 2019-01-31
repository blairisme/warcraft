/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.behaviour.BehaviourType;
import com.evilbird.engine.behaviour.CompositeBehaviour;
import com.evilbird.warcraft.behaviour.ai.AiBehaviour;
import com.evilbird.warcraft.behaviour.hud.HudBehaviour;
import com.evilbird.warcraft.behaviour.user.UserBehaviour;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Arrays;

/**
 * Instances of this class defines the game logic that modifies the state of
 * the game in response to user input, time and other factors.
 *
 * @author Blair Butterworth
 */
public class WarcraftBehaviourFactory implements BehaviourFactory
{
    private Provider<AiBehaviour> aiBehaviourProvider;
    private Provider<HudBehaviour> hudBehaviourProvider;
    private Provider<UserBehaviour> userBehaviourProvider;

    @Inject
    public WarcraftBehaviourFactory(
        Provider<AiBehaviour> aiBehaviourProvider,
        Provider<HudBehaviour> hudBehaviourProvider,
        Provider<UserBehaviour> userBehaviourProvider)
    {
        this.aiBehaviourProvider = aiBehaviourProvider;
        this.hudBehaviourProvider = hudBehaviourProvider;
        this.userBehaviourProvider = userBehaviourProvider;
    }

    @Override
    public void load() {
    }

    @Override
    public Behaviour newBehaviour(BehaviourType type) {
        Behaviour aiBehaviour = aiBehaviourProvider.get();
        Behaviour hudBehaviour = hudBehaviourProvider.get();
        Behaviour userBehaviour = userBehaviourProvider.get();
        return new CompositeBehaviour(Arrays.asList(aiBehaviour, hudBehaviour, userBehaviour));
    }
}
