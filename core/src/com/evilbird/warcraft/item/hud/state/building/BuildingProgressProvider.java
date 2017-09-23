package com.evilbird.warcraft.item.hud.state.building;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.texture.TextureUtils.getDrawable;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BuildingProgressProvider implements AssetProvider<BuildingProgress>
{
    private AssetManager assets;

    @Inject
    public BuildingProgressProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/neutral/perennial/building_progress_bar.png", Texture.class);
        assets.load("data/textures/neutral/perennial/building_progress_background.png", Texture.class);
    }

    @Override
    public BuildingProgress get()
    {
        BuildingProgress result = new BuildingProgress();
        result.setBackground(getDrawable(assets, "data/textures/neutral/perennial/building_progress_background.png"));
        result.setProgressTexture(getDrawable(assets, "data/textures/neutral/perennial/building_progress_bar.png"));
        return result;
    }
}
