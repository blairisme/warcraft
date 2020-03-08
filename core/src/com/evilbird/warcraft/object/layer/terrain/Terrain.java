/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.terrain;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.common.maps.MapLayerIterable;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.object.layer.Layer;
import com.evilbird.warcraft.object.layer.LayerAdapter;
import com.google.gson.annotations.JsonAdapter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.evilbird.engine.common.math.GridPointUtils.comparator;
import static com.evilbird.warcraft.object.layer.LayerUtils.toCellDimensions;

/**
 * Instances of this {@link Layer} represent a physical substance over which
 * game objects traverse, such and land or water.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(LayerAdapter.class)
public class Terrain extends Layer implements SpatialObject
{
    private transient List<GridPoint2> cellLocations;

    @Inject
    public Terrain() {
    }

    @Override
    public void setLayer(TiledMapTileLayer layer) {
        super.setLayer(layer);
        this.cellLocations = new ArrayList<>(layer.getWidth() * layer.getHeight());
        for (MapLayerEntry entry: new MapLayerIterable(layer)) {
            cellLocations.add(entry.getPosition());
        }
    }

    @Override
    public Collection<GameObjectNode> getNodes(GameObjectGraph graph) {
        Collection<GameObjectNode> nodes = new ArrayList<>(cellLocations.size());
        for (GridPoint2 cellLocation: cellLocations) {
            nodes.add(graph.getNode(cellLocation));
        }
        return nodes;
    }

    public TerrainCell getNearestHit(Vector2 position, boolean touchable) {
        if (!touchable || getTouchable()) {
            GridPoint2 location = toCellDimensions(layer, position);
            Lists.sort(cellLocations, comparator(location));
            GridPoint2 nearest = cellLocations.get(0);
            return new TerrainCell(this, nearest);
        }
        return null;
    }

    @Override
    public TerrainCell hit(Vector2 position, boolean touchable) {
        if (touchable && !getTouchable()) { return null; }
        GridPoint2 location = toCellDimensions(layer, position);
        Cell cell = layer.getCell(location.x, location.y);
        return cell != null ? new TerrainCell(this, location) : null;
    }
}
