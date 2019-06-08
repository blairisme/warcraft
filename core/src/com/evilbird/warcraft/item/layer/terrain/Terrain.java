/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.terrain;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemGraphOccupant;
import com.evilbird.warcraft.item.layer.Layer;
import com.evilbird.warcraft.item.layer.LayerAdapter;
import com.evilbird.warcraft.item.unit.Unit;
import com.google.gson.annotations.JsonAdapter;

import javax.inject.Inject;

/**
 * Instances of this {@link Layer} represent the ground below which all land
 * based {@link Unit Units} traverse.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(LayerAdapter.class)
public class Terrain extends Layer implements ItemGraphOccupant
{
    @Inject
    public Terrain() {
    }

    @Override
    public Item hit(Vector2 position, boolean touchable) {
        if (touchable && !getTouchable()) return null;

        int x = Math.round(position.x / layer.getTileWidth());
        int y = Math.round(position.y / layer.getTileHeight());

        TiledMapTileLayer.Cell cell = layer.getCell(x, y);
        return cell != null ? this : null;
    }
}
