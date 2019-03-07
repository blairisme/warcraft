/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.layer.fog.FogProvider;
import com.evilbird.warcraft.item.layer.forest.ForestProvider;
import com.evilbird.warcraft.item.layer.terrain.TerrainFactory;

import javax.inject.Inject;

public class LayerFactory implements IdentifiedAssetProvider<Item> //extends IdentifiedAssetProviderSet<Item>
{
    private TerrainFactory terrainFactory;

    @Inject
    public LayerFactory(TerrainFactory terrainFactory, FogProvider fogProvider, ForestProvider forestProvider) {
//        addProvider(LayerType.Map, terrainFactory);
//        addProvider(LayerType.Forest, forestProvider);
//        addProvider(LayerType.OpaqueFog, fogProvider);
//        addProvider(LayerType.TransparentFog, fogProvider);
        this.terrainFactory = terrainFactory;
    }

    @Override
    public Item get(Identifier identifier) {
        return terrainFactory.get(identifier);
    }

    @Override
    public void load() {
    }
}
