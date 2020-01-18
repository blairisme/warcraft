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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.collection.BitMatrix;
import com.evilbird.engine.object.BasicGameObject;
import com.evilbird.engine.object.GameObjectGroup;

/**
 * Represents a single element in a {@link LayerGroup}. Each LayerGroupCell has
 * a value, which when it reaches zero will cause it to ask its parent group to
 * assign it the style and attributes that constitute an empty cell.
 *
 * @author Blair Butterworth
 */
public class LayerCell extends BasicGameObject
{
    private static final transient int TILE_WIDTH = 32;
    private static final transient int TILE_HEIGHT = 32;
    private static final transient float DEFAULT_VALUE = 100;

    protected float value;
    protected GridPoint2 location;
    protected transient LayerGroupStyle style;

    public LayerCell(LayerGroupStyle style, GridPoint2 location) {
        this(style, location, DEFAULT_VALUE);
    }

    public LayerCell(LayerGroupStyle style, GridPoint2 location, float value) {
        setTouchable(Touchable.enabled);
        setVisible(true);
        setSize(TILE_WIDTH, TILE_HEIGHT);
        setLocation(location);
        setStyle(style);
        setValue(value);
    }

    public GridPoint2 getLocation() {
        return location;
    }

    public float getValue() {
        return value;
    }

    public void setLocation(GridPoint2 location) {
        this.location = location;
        setPosition(location.x * TILE_WIDTH, location.y * TILE_HEIGHT);
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
            layer.setCell(location.x, location.y, style.empty);
        }
    }

    public void showFull() {
        LayerGroup parent = (LayerGroup)getParent();
        if (parent != null) {
            TiledMapTileLayer layer = parent.getLayer();
            layer.setCell(location.x, location.y, style.full);
        }
    }

    public boolean showEdge(BitMatrix edgePattern) {
        Cell edgeStyle = style.patterns.get(edgePattern);
        LayerGroup parent = (LayerGroup)getParent();
        if (parent != null && edgeStyle != null) {
            TiledMapTileLayer layer = parent.getLayer();
            layer.setCell(location.x, location.y, edgeStyle);
            return true;
        }
        return false;
    }
}
