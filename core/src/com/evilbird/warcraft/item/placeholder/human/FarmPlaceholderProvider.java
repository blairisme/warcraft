/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.placeholder.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.placeholder.Placeholder;
import com.evilbird.warcraft.item.placeholder.PlaceholderType;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

public class FarmPlaceholderProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public FarmPlaceholderProvider(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        assets.load("data/textures/human/winter/farm.png", Texture.class);
        assets.load("data/textures/neutral/hud/building_allowed.png", Texture.class);
        assets.load("data/textures/neutral/hud/building_prohibited.png", Texture.class);
    }

    @Override
    public Item get() {
        Placeholder placeholder = new Placeholder();
        placeholder.setBuildingTexture(getDrawable(assets, "data/textures/human/winter/farm.png", 0, 0, 64, 64));
        placeholder.setAllowedTexture(getDrawable(assets, "data/textures/neutral/hud/building_allowed.png", 0, 0, 64, 64));
        placeholder.setProhibitedTexture(getDrawable(assets, "data/textures/neutral/hud/building_prohibited.png", 0, 0, 64, 64));
        placeholder.setType(PlaceholderType.FarmPlaceholder);
        placeholder.setSize(64, 64);
        return placeholder;
    }
}