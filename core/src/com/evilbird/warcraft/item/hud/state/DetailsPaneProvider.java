package com.evilbird.warcraft.item.hud.state;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.hud.common.UnitPaneProvider;

import javax.inject.Inject;

import static com.evilbird.warcraft.common.TextureUtils.getDrawable;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class DetailsPaneProvider implements AssetProvider<DetailsPane>
{
    private AssetManager assets;
    private UnitPaneProvider unitPaneProvider;
    private com.evilbird.warcraft.item.hud.state.building.BuildingBarProvider buildingBarProvider;

    @Inject
    public DetailsPaneProvider(
        Device device,
        UnitPaneProvider unitPaneProvider,
        com.evilbird.warcraft.item.hud.state.building.BuildingBarProvider buildingBarProvider)
    {
        this.assets = device.getAssetStorage().getAssets();
        this.unitPaneProvider = unitPaneProvider;
        this.buildingBarProvider = buildingBarProvider;
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/hud/details_panel.png", Texture.class);
    }

    @Override
    public DetailsPane get()
    {
        DetailsPane detailsPane = new DetailsPane(unitPaneProvider, buildingBarProvider);
        detailsPane.setBackground(getDrawable(assets, "data/textures/human/hud/details_panel.png"));
        return detailsPane;
    }
}
