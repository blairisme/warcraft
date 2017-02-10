package com.evilbird.warcraft.hud;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.utility.Identifier;

public class HudFactory
{
    private AssetManager assets;

    public HudFactory(AssetManager assets)
    {
        this.assets = assets;
    }

    public void loadAssets()
    {
        assets.load("data/textures/neutral/hud/resource-icon.png", Texture.class);
        assets.load("data/textures/human/hud/resource.png", Texture.class);
    }

    public com.evilbird.engine.hud.Hud newHud(Identifier identifier)
    {
        com.evilbird.engine.hud.Hud hud = new com.evilbird.engine.hud.Hud();
        hud.addActor(new HudResources(assets));
        return hud;
    }
}
