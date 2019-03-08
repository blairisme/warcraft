/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.maps.TiledMapFile;
import com.evilbird.engine.common.maps.TiledMapLoader;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Forest} instances, loading the
 * appropriate assets to display the forest, and its cells, in different
 * states.
 *
 * @author Blair Butterworth
 */
public class ForestFactory implements IdentifiedAssetProvider<Forest>
{
    private static final String TERRAIN = "data/textures/neutral/winter/terrain.png";
    private AssetManager assets;
    private TiledMapLoader loader;

    @Inject
    public ForestFactory(Device device, TiledMapLoader loader) {
        this.assets = device.getAssetStorage();
        this.loader = loader;
    }

    @Override
    public void load() {
        assets.load(TERRAIN, Texture.class);
    }

    @Override
    public Forest get(Identifier identifier) {
        Validate.isInstanceOf(LayerIdentifier.class, identifier);
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;

        Forest forest = new Forest();
        forest.setSkin(getSkin());
        forest.setId(layerIdentifier);
        forest.setType(layerIdentifier.getType());
        forest.setLayer(getLayer(layerIdentifier));
        forest.setTouchable(Touchable.childrenOnly);
        return forest;
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

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getForestStyle());
        return skin;
    }

    private ForestStyle getForestStyle() {
        ForestStyle forestStyle = new ForestStyle();
        //forestStyle.deadCenter = getRegion(assets, TERRAIN, 448, 224, 32, 32);
        return forestStyle;
    }
}
