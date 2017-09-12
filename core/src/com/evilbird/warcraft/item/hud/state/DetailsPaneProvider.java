package com.evilbird.warcraft.item.hud.state;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.hud.common.UnitPaneProvider;
import com.evilbird.warcraft.item.hud.state.building.BuildingProgressProvider;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.TextureUtils.getDrawable;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class DetailsPaneProvider implements AssetProvider<DetailsPane>
{
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
    public void load()
    {
        assets.load("data/textures/human/hud/details_panel.png", Texture.class);
    }

    @Override
    public DetailsPane get()
    {
        DetailsPane detailsPane = new DetailsPane(unitPaneProvider, buildingProgressProvider);
        detailsPane.setBackground(getDrawable(assets, "data/textures/human/hud/details_panel.png"));
        return detailsPane;
    }
}
