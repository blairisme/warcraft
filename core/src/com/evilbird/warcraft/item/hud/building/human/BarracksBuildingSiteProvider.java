package com.evilbird.warcraft.item.hud.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.building.BuildingSite;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BarracksBuildingSiteProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public BarracksBuildingSiteProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/winter/barracks.png", Texture.class);
        assets.load("data/textures/neutral/hud/building_allowed.png", Texture.class);
        assets.load("data/textures/neutral/hud/building_prohibited.png", Texture.class);
    }

    @Override
    public Item get()
    {
        BuildingSite buildingSite = new BuildingSite();
        buildingSite.setBuildingTexture(getTexture("data/textures/human/winter/barracks.png"));
        buildingSite.setAllowedTexture(getTexture("data/textures/neutral/hud/building_allowed.png"));
        buildingSite.setProhibitedTexture(getTexture("data/textures/neutral/hud/building_prohibited.png"));
        buildingSite.setType(new NamedIdentifier("BarracksBuildingSite"));
        buildingSite.setSize(96, 96);
        return buildingSite;
    }

    private Drawable getTexture(String path)
    {
        Texture texture = assets.get(path);
        TextureRegion region = new TextureRegion(texture, 0, 0, 96, 96);
        return new TextureRegionDrawable(region);
    }
}
