package com.evilbird.engine.behaviour;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.utility.Identifier;

public interface BehaviourFactory
{
    public void load(AssetManager assetManager);

    public Behaviour newBehaviour(Identifier id);
}
