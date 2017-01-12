package com.evilbird.warcraft.hud;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.warcraft.utility.Identifier;

public class HudFactory
{
    private AssetManager assets;

    public HudFactory(AssetManager assets)
    {
        this.assets = assets;
    }

    public Hud newHud(Identifier identifier)
    {
        return null;
    }
}
