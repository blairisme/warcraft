/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.layer.fog.FogFactory;
import com.evilbird.warcraft.item.layer.forest.ForestFactory;
import com.evilbird.warcraft.item.layer.terrain.TerrainFactory;

import javax.inject.Inject;

/**
 * Instances of this factory are used to construct {@link Layer} items.
 *
 * @author Blair Butterworth
 */
public class LayerFactory implements IdentifiedAssetProvider<Item>
{
    private FogFactory fogFactory;
    private ForestFactory forestFactory;
    private TerrainFactory terrainFactory;

    @Inject
    public LayerFactory(FogFactory fogFactory, ForestFactory forestFactory, TerrainFactory terrainFactory) {
        this.fogFactory = fogFactory;
        this.forestFactory = forestFactory;
        this.terrainFactory = terrainFactory;
    }

    @Override
    public Item get(Identifier identifier) {
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;
        switch (layerIdentifier.getType()) {
            case Map: return terrainFactory.get(identifier);
            case Forest: return forestFactory.get(identifier);
            case OpaqueFog: return fogFactory.get(identifier);
            case TransparentFog: return fogFactory.get(identifier);
            default: throw new UnknownEntityException(identifier);
        }
    }

    @Override
    public void load() {
        fogFactory.load();
        forestFactory.load();
        terrainFactory.load();
    }
}
