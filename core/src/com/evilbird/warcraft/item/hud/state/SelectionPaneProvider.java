package com.evilbird.warcraft.item.hud.state;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.hud.common.UnitPaneProvider;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.texture.TextureUtils.getDrawable;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SelectionPaneProvider implements AssetProvider<SelectionPane>
{
    private AssetManager assets;
    private UnitPaneProvider tileProvider;

    @Inject
    public SelectionPaneProvider(Device device, UnitPaneProvider tileProvider)
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
        result.setBackground(getDrawable(assets, "data/textures/human/hud/selection_panel.png"));
        return result;
    }
}
