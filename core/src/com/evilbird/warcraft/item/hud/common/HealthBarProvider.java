package com.evilbird.warcraft.item.hud.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.TextureUtils.getDrawable;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class HealthBarProvider implements AssetProvider<HealthBar>
{
    private AssetManager assets;

    @Inject
    public HealthBarProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/neutral/perennial/health_bar_high.png", Texture.class);
        assets.load("data/textures/neutral/perennial/health_bar_medium.png", Texture.class);
        assets.load("data/textures/neutral/perennial/health_bar_low.png", Texture.class);
    }

    @Override
    public HealthBar get()
    {
        HealthBar result = new HealthBar();
        result.setHighHealthTexture(getDrawable(assets, "data/textures/neutral/perennial/health_bar_high.png"));
        result.setMediumHealthTexture(getDrawable(assets, "data/textures/neutral/perennial/health_bar_medium.png"));
        result.setLowHealthTexture(getDrawable(assets, "data/textures/neutral/perennial/health_bar_low.png"));
        return result;
    }
}
