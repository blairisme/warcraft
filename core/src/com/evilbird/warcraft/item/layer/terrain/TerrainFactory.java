/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.terrain;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.common.maps.TiledMapFile;
import com.evilbird.engine.common.maps.TiledMapLoader;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Terrain} layers.
 *
 * @author Blair Butterworth
 */
public class TerrainFactory implements IdentifiedAssetProvider<Terrain>
{
    private TiledMapLoader loader;

    @Inject
    public TerrainFactory() {
        loader = new TiledMapLoader();
    }

    @Override
    public void load() {
    }

    @Override
    public Terrain get(Identifier identifier) {
        Validate.isInstanceOf(LayerIdentifier.class, identifier);
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;

        Terrain terrain = new Terrain();
        terrain.setId(layerIdentifier);
        terrain.setType(layerIdentifier.getType());
        terrain.setLayer(getLayer(layerIdentifier));
        return terrain;
    }

    private TiledMapTileLayer getLayer(LayerIdentifier type) {
        TiledMapTileLayer layer = type.getLayer();
        if (layer == null) {
            TiledMapFile map = loader.load(type.getFile());
            MapLayers layers = map.getLayers();
            layer = (TiledMapTileLayer)layers.get(type.getName());
        }
        return layer;
    }
}
