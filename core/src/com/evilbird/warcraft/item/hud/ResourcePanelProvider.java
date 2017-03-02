package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evilbird.engine.common.inject.AssetObjectProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ResourcePanelProvider implements AssetObjectProvider<Item>
{
    private AssetManager assets;

    @Inject
    public ResourcePanelProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        this.assets.load("data/textures/neutral/hud/resource-icon.png", Texture.class);
        this.assets.load("data/textures/human/hud/resource_panel.png", Texture.class);
    }

    @Override
    public Item get()
    {
        ResourcePanel result = new ResourcePanel();
        result.setBackground(getBackground());
        result.setGoldIcon(getGoldIcon());
        result.setGoldText("0");
        result.setOilIcon(getOilIcon());
        result.setOilText("0");
        result.setWoodIcon(getWoodIcon());
        result.setWoodText("0");
        return result;
    }

    private TextureRegion getBackground()
    {
        Texture texture = assets.get("data/textures/human/hud/resource_panel.png");
        TextureRegion region = new TextureRegion(texture);
        return region;
    }

    private TextureRegion getGoldIcon()
    {
        Texture texture = assets.get("data/textures/neutral/hud/resource-icon.png");
        TextureRegion region = new TextureRegion(texture, 0, 0, 14, 14);
        return region;
    }

    private TextureRegion getOilIcon()
    {
        Texture texture = assets.get("data/textures/neutral/hud/resource-icon.png");
        TextureRegion region = new TextureRegion(texture, 0, 28, 14, 14);
        return region;
    }

    private TextureRegion getWoodIcon()
    {
        Texture texture = assets.get("data/textures/neutral/hud/resource-icon.png");
        TextureRegion region = new TextureRegion(texture, 0, 14, 14, 14);
        return region;
    }
}
