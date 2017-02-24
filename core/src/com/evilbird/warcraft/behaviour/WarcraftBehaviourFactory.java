package com.evilbird.warcraft.behaviour;

import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.behaviour.CompositeBehaviour;
import com.evilbird.engine.utility.Identifier;

import java.util.Arrays;

import javax.inject.Inject;

public class WarcraftBehaviourFactory implements BehaviourFactory
{
    private ActionFactory actionFactory;

    @Inject
    public WarcraftBehaviourFactory(ActionFactory actionFactory)
    {
        this.actionFactory = actionFactory;
    }

    public void load()
    {
    }

    public Behaviour newBehaviour(Identifier id)
    {
        Behaviour hudBehaviour = new HudBehaviour();
        Behaviour userBehaviour = new InteractionAnalyzer(actionFactory);
        return new CompositeBehaviour(Arrays.asList(hudBehaviour, userBehaviour));
    }


}
