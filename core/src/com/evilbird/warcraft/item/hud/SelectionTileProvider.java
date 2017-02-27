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
public class SelectionTileProvider implements AssetObjectProvider<SelectionTile>
{
    private AssetManager assets;
    private HealthBarProvider healthBarProvider;

    @Inject
    public SelectionTileProvider(
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
    public SelectionTile get()
    {
        SelectionTile result = new SelectionTile(healthBarProvider);
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
