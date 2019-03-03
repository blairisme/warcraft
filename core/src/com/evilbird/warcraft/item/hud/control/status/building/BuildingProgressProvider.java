/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.building;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

/**
 * Instances of this factory create {@link BuildingProgress} objects.
 *
 * @author Blair Butterworth
 */
public class BuildingProgressProvider implements AssetProvider<BuildingProgress>
{
    private static final String PROGRESS_FILL = "data/textures/neutral/perennial/building_progress_bar.png";
    private static final String PROGRESS_BACKGROUND = "data/textures/neutral/perennial/building_progress_background.png";
    private AssetManager assets;

    @Inject
    public BuildingProgressProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load(PROGRESS_FILL, Texture.class);
        assets.load(PROGRESS_BACKGROUND, Texture.class);
    }

    @Override
    public BuildingProgress get() {
        BuildingProgress result = new BuildingProgress();
        result.setBackground(getDrawable(assets, PROGRESS_BACKGROUND));
        result.setProgressTexture(getDrawable(assets, PROGRESS_FILL));
        return result;
    }
}
