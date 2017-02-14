package com.evilbird.engine.menu;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.utility.Identifier;

public interface MenuFactory
{
    public void load(AssetManager assetManager);

    public Menu newMenu(Identifier id);
}
