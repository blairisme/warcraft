package com.evilbird.engine.behaviour;

import com.evilbird.engine.common.lang.Identifier;

public interface BehaviourFactory
{
    public void load();

    public Behaviour newBehaviour(Identifier id);
}
