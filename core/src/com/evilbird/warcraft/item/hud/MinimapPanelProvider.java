package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.utility.AssetObjectProvider;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class MinimapPanelProvider implements AssetObjectProvider<MinimapPanel>
{
    private AssetManager assets;

    @Inject
    public MinimapPanelProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/hud/minimap_panel.png", Texture.class);
    }

    @Override
    public MinimapPanel get()
    {
        MinimapPanel result = new MinimapPanel();
        result.setBackground(getTexture("data/textures/human/hud/minimap_panel.png"));
        return result;
    }

    private Drawable getTexture(String path)
    {
        Texture texture = assets.get(path);
        TextureRegion region = new TextureRegion(texture);
        return new TextureRegionDrawable(region);
    }
}
