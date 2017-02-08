package com.evilbird.warcraft.interaction;

import com.evilbird.warcraft.action.ActionFactory;
import com.evilbird.warcraft.utility.Identifier;

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

    public Behaviour newBehaviour(Identifier id)
    {
        Behaviour hudBehaviour = new HudBehaviour();
        Behaviour userBehaviour = new InteractionAnalyzer(actionFactory);
        return new CompositeBehaviour(Arrays.asList(hudBehaviour, userBehaviour));
    }
}
