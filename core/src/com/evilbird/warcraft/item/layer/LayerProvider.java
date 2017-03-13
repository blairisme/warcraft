package com.evilbird.warcraft.item.layer;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this REPLACEME
 *
 * @Author Blair
 */

public class LayerProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public LayerProvider(Provider<Map> mapProvider, FogProvider fogProvider, Provider<Wood> woodProvider)
    {
        addProvider(LayerType.Map, mapProvider);
        addProvider(LayerType.OpaqueFog, fogProvider);
        addProvider(LayerType.TransparentFog, fogProvider);

        addProvider(LayerType.Wood, woodProvider);
    }
}
