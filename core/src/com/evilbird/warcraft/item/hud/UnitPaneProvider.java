package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.inject.AssetObjectProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class UnitPaneProvider implements AssetObjectProvider<UnitPane>
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
        result.setBackground(getTexture("data/textures/neutral/perennial/selection.png"));
        return result;
    }

    private Drawable getTexture(String path)
    {
        Texture texture = assets.get(path);
        TextureRegion region = new TextureRegion(texture);
        return new TextureRegionDrawable(region);
    }
}
