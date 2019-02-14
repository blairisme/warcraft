/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.layer.fog.FogProvider;
import com.evilbird.warcraft.item.layer.forest.ForestProvider;
import com.evilbird.warcraft.item.layer.map.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class LayerProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public LayerProvider(
        Provider<Map> mapProvider,
        FogProvider fogProvider,
        ForestProvider forestProvider)
    {
        addProvider(LayerType.Map, mapProvider);
        addProvider(LayerType.Forest, forestProvider);
        addProvider(LayerType.OpaqueFog, fogProvider);
        addProvider(LayerType.TransparentFog, fogProvider);
    }
}
