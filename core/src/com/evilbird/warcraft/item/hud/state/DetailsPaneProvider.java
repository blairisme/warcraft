package com.evilbird.warcraft.item.hud.state;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.hud.common.UnitPaneProvider;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class DetailsPaneProvider implements AssetProvider<DetailsPane>
{
    private AssetManager assets;
    private UnitPaneProvider unitPaneProvider;

    @Inject
    public DetailsPaneProvider(Device device, UnitPaneProvider unitPaneProvider)
    {
        this.assets = device.getAssetStorage().getAssets();
        this.unitPaneProvider = unitPaneProvider;
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/hud/details_panel.png", Texture.class);
    }

    @Override
    public DetailsPane get()
    {
        DetailsPane detailsPane = new DetailsPane(unitPaneProvider);
        detailsPane.setBackground(getTexture("data/textures/human/hud/details_panel.png"));
        return detailsPane;
    }

    private Drawable getTexture(String path)
    {
        Texture texture = assets.get(path);
        TextureRegion region = new TextureRegion(texture);
        return new TextureRegionDrawable(region);
    }
}
