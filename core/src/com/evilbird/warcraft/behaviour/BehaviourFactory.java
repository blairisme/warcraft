package com.evilbird.warcraft.behaviour;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.action.ActionFactory;

import java.util.Arrays;

public class BehaviourFactory
{
    private ActionFactory actionFactory;

    public BehaviourFactory(ActionFactory actionFactory)
    {
        this.actionFactory = actionFactory;
    }

    public void loadAssets()
    {
    }

    public com.evilbird.engine.behaviour.Behaviour newBehaviour(Identifier id)
    {
        com.evilbird.engine.behaviour.Behaviour hudBehaviour = new HudBehaviour();
        Behaviour userBehaviour = new InteractionAnalyzer(actionFactory);
        return new com.evilbird.engine.behaviour.CompositeBehaviour(Arrays.asList(hudBehaviour, userBehaviour));
    }
}
