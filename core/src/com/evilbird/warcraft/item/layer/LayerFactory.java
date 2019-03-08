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
import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.layer.fog.FogProvider;
import com.evilbird.warcraft.item.layer.forest.ForestFactory;
import com.evilbird.warcraft.item.layer.terrain.TerrainFactory;

import javax.inject.Inject;

/**
 * Instances of this factory are used to construct {@link Layer} items.
 *
 * @author Blair Butterworth
 */
public class LayerFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public LayerFactory(
        FogProvider fogProvider,
        ForestFactory forestFactory,
        TerrainFactory terrainFactory)
    {
        addProvider(LayerType.Map, terrainFactory);
        addProvider(LayerType.Forest, forestFactory);
        addProvider(LayerType.OpaqueFog, fogProvider);
        addProvider(LayerType.TransparentFog, fogProvider);
    }

    @Override
    public Item get(Identifier identifier) {
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;
        IdentifiedAssetProvider<? extends Item> factory = getProvider(layerIdentifier.getType());

        if (factory != null) {
            return factory.get(layerIdentifier);
        }
        throw new UnknownEntityException(identifier);
    }
}
