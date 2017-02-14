package com.evilbird.engine.world;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.utility.Identifier;

public interface WorldFactory
{
    public void load(AssetManager assets);

    public World newWorld(Identifier identifier);
}
