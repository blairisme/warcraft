/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.terrain;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.common.maps.MapLayerIterable;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.object.layer.Layer;
import com.evilbird.warcraft.object.layer.LayerAdapter;
import com.evilbird.warcraft.object.unit.Unit;
import com.google.gson.annotations.JsonAdapter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

import static com.evilbird.warcraft.object.layer.LayerUtils.toCellDimensions;

/**
 * Instances of this {@link Layer} represent the ground below which all land
 * based {@link Unit Units} traverse.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(LayerAdapter.class)
public class Terrain extends Layer implements SpatialObject
{
    @Inject
    public Terrain() {
    }

    @Override
    public Collection<GameObjectNode> getNodes(GameObjectGraph graph) {
        Collection<GameObjectNode> nodes = new ArrayList<>(layer.getWidth() * layer.getHeight());
        for (MapLayerEntry entry: new MapLayerIterable(layer)) {
            nodes.add(graph.getNode(entry.getPosition()));
        }
        return nodes;
    }

    @Override
    public GameObject hit(Vector2 position, boolean touchable) {
        if (touchable && !getTouchable()) { return null; }
        GridPoint2 location = toCellDimensions(layer, position);
        Cell cell = layer.getCell(location.x, location.y);
        return cell != null ? this : null;
    }
}
