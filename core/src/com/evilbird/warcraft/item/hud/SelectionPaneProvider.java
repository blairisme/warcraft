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
public class SelectionPaneProvider implements AssetObjectProvider<SelectionPane>
{
    private AssetManager assets;
    private UnitPaneProvider tileProvider;

    @Inject
    public SelectionPaneProvider(
        Device device,
        UnitPaneProvider tileProvider)
    {
        this.assets = device.getAssetStorage().getAssets();
        this.tileProvider = tileProvider;
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/hud/selection_panel.png", Texture.class);
    }

    @Override
    public SelectionPane get()
    {
        SelectionPane result = new SelectionPane(tileProvider);
        result.setBackground(getTexture("data/textures/human/hud/selection_panel.png"));
        return result;
    }

    private Drawable getTexture(String path)
    {
        Texture texture = assets.get(path);
        TextureRegion region = new TextureRegion(texture);
        return new TextureRegionDrawable(region);
    }
}
