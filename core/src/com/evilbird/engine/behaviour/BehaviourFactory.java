package com.evilbird.engine.behaviour;

public interface BehaviourFactory
{
    public void load();

    public Behaviour newBehaviour(BehaviourType type);
}
