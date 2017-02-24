package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.utility.AssetObjectProvider;

import javax.inject.Inject;

public class HumanHud implements AssetObjectProvider<ItemGroup>
{
    private AssetManager assets;

    @Inject
    public HumanHud(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        this.assets.load("data/textures/neutral/hud/resource-icon.png", Texture.class);
        this.assets.load("data/textures/human/hud/resource.png", Texture.class);
    }

    @Override
    public ItemGroup get()
    {
        ItemGroup hud = new ItemGroup();
        hud.addActor(new ResourceBar(assets));
        return hud;
    }
}
