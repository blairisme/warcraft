/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.evilbird.engine.common.collection.BitMatrix;
import com.evilbird.warcraft.object.layer.LayerGroupStyle;

import java.util.Map;

import static com.evilbird.warcraft.object.layer.fog.FogPattern.Empty;
import static com.evilbird.warcraft.object.layer.fog.FogPattern.Full;

/**
 * Specifies the style of a {@link ConcealingFogCell} and the surrounding
 * cells.
 *
 * @author Blair Butterworth
 */
public class ConcealingFogStyle extends LayerGroupStyle
{
    public ConcealingFogStyle(LayerGroupStyle style) {
        super(style);
    }

    public void setFull(Cell full) {
        super.setFull(full);
        setPattern(Full, full);
    }

    public void setEmpty(Cell empty) {
        super.setEmpty(empty);
        setPattern(Empty, empty);
    }

    private void setPattern(FogPattern fogPattern, Cell value) {
        for (Map.Entry<BitMatrix, Cell> patternEntry: getPatterns().entrySet()) {
            BitMatrix pattern = patternEntry.getKey();
            if (pattern.getId() == fogPattern) {
                patternEntry.setValue(value);
            }
        }
    }
}
