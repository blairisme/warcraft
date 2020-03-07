/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.evilbird.engine.common.collection.BitMatrix;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Specifies the style of a {@link LayerGroupCell} and the surrounding cells.
 * 
 * @author Blair Butterworth
 */
public class LayerGroupStyle
{
    private Cell empty;
    private Cell full;
    private Map<BitMatrix, Cell> patterns;

    public LayerGroupStyle() {
        this.empty = null;
        this.full = null;
        this.patterns = Collections.emptyMap();
    }

    public LayerGroupStyle(LayerGroupStyle style) {
        this.empty = style.empty;
        this.full = style.full;
        this.patterns = new HashMap<>(style.patterns);
    }

    public Cell getEmpty() {
        return empty;
    }

    public Cell getFull() {
        return full;
    }

    public Map<BitMatrix, Cell> getPatterns() {
        return patterns;
    }

    public void setFull(Cell full) {
        this.full = full;
    }

    public void setEmpty(Cell empty) {
        this.empty = empty;
    }

    public void setPatterns(Map<BitMatrix, Cell> patterns) {
        this.patterns = patterns;
    }
}
