/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link ResourcePane ResourcePanes}.
 *
 * @author Blair Butterworth
 */
public class ResourcePanelProvider implements AssetProvider<Item>
{
    private static final String ICONS = "data/textures/neutral/hud/resource-icon.png";
    private static final String BACKGROUND = "data/textures/human/hud/resource_panel.png";
    private AssetManager assets;

    @Inject
    public ResourcePanelProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        this.assets.load(ICONS, Texture.class);
        this.assets.load(BACKGROUND, Texture.class);
    }

    @Override
    public Item get() {
        ResourcePane result = new ResourcePane();
        result.setBackground(getBackground());
        result.setGoldIcon(getGoldIcon());
        result.setGoldText("0");
        result.setOilIcon(getOilIcon());
        result.setOilText("0");
        result.setWoodIcon(getWoodIcon());
        result.setWoodText("0");
        return result;
    }

    private TextureRegion getBackground() {
        Texture texture = assets.get(BACKGROUND);
        TextureRegion region = new TextureRegion(texture);
        return region;
    }

    private TextureRegion getGoldIcon() {
        Texture texture = assets.get(ICONS);
        TextureRegion region = new TextureRegion(texture, 0, 0, 14, 14);
        return region;
    }

    private TextureRegion getOilIcon() {
        Texture texture = assets.get(ICONS);
        TextureRegion region = new TextureRegion(texture, 0, 28, 14, 14);
        return region;
    }

    private TextureRegion getWoodIcon() {
        Texture texture = assets.get(ICONS);
        TextureRegion region = new TextureRegion(texture, 0, 14, 14, 14);
        return region;
    }
}
