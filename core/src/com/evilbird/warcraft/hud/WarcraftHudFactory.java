package com.evilbird.warcraft.hud;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.hud.Hud;
import com.evilbird.engine.hud.HudFactory;
import com.evilbird.engine.utility.Identifier;

public class WarcraftHudFactory implements HudFactory
{
    private AssetManager assetManager;

    public WarcraftHudFactory()
    {
    }

    public void load(AssetManager assetManager)
    {
        this.assetManager = assetManager;
        this.assetManager.load("data/textures/neutral/hud/resource-icon.png", Texture.class);
        this.assetManager.load("data/textures/human/hud/resource.png", Texture.class);
    }

    public Hud newHud(Identifier identifier)
    {
        Hud hud = new Hud();
        hud.addActor(new ResourceBar(assetManager));
        return hud;
    }
}
