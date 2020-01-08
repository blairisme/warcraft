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

import java.util.Map;

/**
 * Specifies the style of a {@link LayerCell} and the surrounding cells.
 * 
 * @author Blair Butterworth
 */
public class LayerGroupStyle
{
    public Cell empty;
    public Cell full;
    public Map<BitMatrix, Cell> patterns;
}
