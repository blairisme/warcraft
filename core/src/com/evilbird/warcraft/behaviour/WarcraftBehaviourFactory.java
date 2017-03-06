package com.evilbird.warcraft.behaviour;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.behaviour.CompositeBehaviour;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.behaviour.hud.HudBehaviour;
import com.evilbird.warcraft.behaviour.user.UserBehaviour;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Provider;

public class WarcraftBehaviourFactory implements BehaviourFactory
{
    private Provider<HudBehaviour> hudBehaviourProvider;
    private Provider<UserBehaviour> userBehaviourProvider;

    @Inject
    public WarcraftBehaviourFactory(
        Provider<HudBehaviour> hudBehaviourProvider,
        Provider<UserBehaviour> userBehaviourProvider)
    {
        this.hudBehaviourProvider = hudBehaviourProvider;
        this.userBehaviourProvider = userBehaviourProvider;
    }

    @Override
    public void load()
    {
    }

    @Override
    public Behaviour newBehaviour(Identifier id)
    {
        Behaviour hudBehaviour = hudBehaviourProvider.get();
        Behaviour userBehaviour = userBehaviourProvider.get();
        return new CompositeBehaviour(Arrays.asList(hudBehaviour, userBehaviour));
    }
}
