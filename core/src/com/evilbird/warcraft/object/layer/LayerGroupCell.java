/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.collection.BitMatrix;
import com.evilbird.engine.object.GameObjectGroup;

/**
 * Represents a single element in a {@link LayerGroup}. Each LayerGroupCell has
 * a value, which when it reaches zero will cause it to ask its parent group to
 * assign it the style and attributes that constitute an empty cell.
 *
 * @author Blair Butterworth
 */
public class LayerGroupCell extends LayerCell
{
    private static final transient float DEFAULT_VALUE = 100;

    protected float value;
    protected transient LayerGroupStyle style;

    public LayerGroupCell(LayerGroupStyle style, GridPoint2 location) {
        this(style, location, DEFAULT_VALUE);
    }

    public LayerGroupCell(LayerGroupStyle style, GridPoint2 location, float value) {
        super(location);
        setStyle(style);
        setValue(value);
    }

    public float getValue() {
        return value;
    }

    public void setStyle(LayerGroupStyle style) {
        this.style = style;
    }

    public void setValue(float value) {
        float previous = this.value;
        this.value = Math.max(value, 0f);

        if (value == 0f && previous != 0f) {
            showEmpty();
        }
        if (value != 0f && previous == 0f) {
            showFull();
        }
    }

    @Override
    public void setParent(GameObjectGroup parent) {
        super.setParent(parent);
        if (value == 0f) {
            showEmpty();
        } else {
            showFull();
        }
    }

    public void showEmpty() {
        LayerGroup parent = (LayerGroup)getParent();
        if (parent != null) {
            TiledMapTileLayer layer = parent.getLayer();
            layer.setCell(location.x, location.y, style.getEmpty());
        }
    }

    public void showFull() {
        LayerGroup parent = (LayerGroup)getParent();
        if (parent != null) {
            TiledMapTileLayer layer = parent.getLayer();
            layer.setCell(location.x, location.y, style.getFull());
        }
    }

    public boolean showEdge(BitMatrix edgePattern) {
        LayerGroup parent = (LayerGroup)getParent();
        if (parent != null) {
            showPattern(parent.getLayer(), edgePattern);
            return true;
        }
        return false;
    }

    private void showPattern(TiledMapTileLayer layer, BitMatrix pattern) {
        Cell cell = style.getPatterns().get(pattern);
        if (cell != null) {
            layer.setCell(location.x, location.y, cell);
        } else {
            showEmpty();
        }
    }
}
