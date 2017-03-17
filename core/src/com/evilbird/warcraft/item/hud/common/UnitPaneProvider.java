package com.evilbird.warcraft.item.hud.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

import static com.evilbird.warcraft.common.TextureUtils.getDrawable;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class UnitPaneProvider implements AssetProvider<UnitPane>
{
    private AssetManager assets;
    private HealthBarProvider healthBarProvider;

    @Inject
    public UnitPaneProvider(
        Device device,
        HealthBarProvider healthBarProvider)
    {
        this.assets = device.getAssetStorage().getAssets();
        this.healthBarProvider = healthBarProvider;
    }

    @Override
    public void load()
    {
        assets.load("data/textures/neutral/perennial/selection.png", Texture.class);
    }

    @Override
    public UnitPane get()
    {
        UnitPane result = new UnitPane(healthBarProvider);
        result.setBackground(getDrawable(assets, "data/textures/neutral/perennial/selection.png"));
        return result;
    }
}
