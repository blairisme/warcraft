/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.object.BasicGameObject;

/**
 * Represents a single element in a {@link Layer}.
 *
 * @author Blair Butterworth
 */
public class LayerCell extends BasicGameObject
{
    protected transient GridPoint2 location;
    protected transient float cellHeight;
    protected transient float cellWidth;

    public LayerCell(Layer parent, GridPoint2 location) {
        TiledMapTileLayer layer = parent.getLayer();
        this.cellHeight = layer.getTileHeight();
        this.cellWidth = layer.getTileWidth();

        setTouchable(Touchable.enabled);
        setVisible(true);
        setSize(cellWidth, cellHeight);
        setLocation(location);
    }

    public GridPoint2 getLocation() {
        return location;
    }

    public void setLocation(GridPoint2 location) {
        this.location = location;
        setPosition(location.x * cellWidth, location.y * cellHeight);
    }
}
