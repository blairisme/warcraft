package com.evilbird.warcraft.item.unit.site.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.site.BuildSite;
import com.evilbird.warcraft.item.unit.site.BuildSiteType;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.texture.TextureUtils.getDrawable;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class TownHallBuildSiteProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public TownHallBuildSiteProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/winter/town_hall.png", Texture.class);
        assets.load("data/textures/neutral/hud/building_allowed.png", Texture.class);
        assets.load("data/textures/neutral/hud/building_prohibited.png", Texture.class);
    }

    @Override
    public Item get()
    {
        BuildSite buildSite = new BuildSite();
        buildSite.setBuildingTexture(getDrawable(assets, "data/textures/human/winter/town_hall.png", 0, 0, 128, 128));
        buildSite.setAllowedTexture(getDrawable(assets, "data/textures/neutral/hud/building_allowed.png", 0, 0, 128, 128));
        buildSite.setProhibitedTexture(getDrawable(assets, "data/textures/neutral/hud/building_prohibited.png", 0, 0, 128, 128));
        buildSite.setType(BuildSiteType.TownHall);
        buildSite.setSize(128, 128);
        return buildSite;
    }
}