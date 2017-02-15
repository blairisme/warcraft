package com.evilbird.engine.behaviour;

import com.evilbird.engine.utility.Identifier;

public interface BehaviourFactory
{
    public void load();

    public Behaviour newBehaviour(Identifier id);
}
