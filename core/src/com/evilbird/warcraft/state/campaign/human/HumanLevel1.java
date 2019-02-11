/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.campaign.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.state.common.ItemRootFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.assets.AssetUtilities.linearFilter;

public class HumanLevel1 implements AssetProvider<ItemRoot>
{
    private static final String MAP = "data/levels/human/level1.tmx";
    private AssetManager assets;
    private ItemRootFactory itemRootFactory;

    @Inject
    public HumanLevel1(Device device, ItemRootFactory itemRootFactory) {
        this.assets = device.getAssetStorage().getAssets();
        this.itemRootFactory = itemRootFactory;
    }

    @Override
    public void load() {
    }

    @Override
    public ItemRoot get() {
        assets.load(MAP, TiledMap.class, linearFilter());
        assets.finishLoadingAsset(MAP);
        TiledMap tiledMap =  assets.get(MAP, TiledMap.class);
        return itemRootFactory.load(tiledMap);
    }
}
