package com.evilbird.engine.hud;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.utility.Identifier;

public interface HudFactory
{
    public void load(AssetManager assetManager);

    public Hud newHud(Identifier identifier);
}
