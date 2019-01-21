/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.hud.common.UnitPaneProvider;
import com.evilbird.warcraft.item.hud.control.status.building.BuildingProgressProvider;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.texture.TextureUtils.getDrawable;

/**
 * Instances of this factory create {@link DetailsPane DetailsPanes}.
 *
 * @author Blair Butterworth
 */
public class DetailsPaneProvider implements AssetProvider<DetailsPane>
{
    private static final String BACKGROUND = "data/textures/human/hud/details_panel.png";
    private AssetManager assets;
    private UnitPaneProvider unitPaneProvider;
    private BuildingProgressProvider buildingProgressProvider;

    @Inject
    public DetailsPaneProvider(
        Device device,
        UnitPaneProvider unitPaneProvider,
        BuildingProgressProvider buildingProgressProvider)
    {
        this.assets = device.getAssetStorage().getAssets();
        this.unitPaneProvider = unitPaneProvider;
        this.buildingProgressProvider = buildingProgressProvider;
    }

    @Override
    public void load() {
        assets.load(BACKGROUND, Texture.class);
    }

    @Override
    public DetailsPane get() {
        DetailsPane detailsPane = new DetailsPane(unitPaneProvider, buildingProgressProvider);
        detailsPane.setBackground(getDrawable(assets, BACKGROUND));
        return detailsPane;
    }
}
