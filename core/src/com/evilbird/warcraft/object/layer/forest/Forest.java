/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.forest;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.layer.LayerCell;
import com.evilbird.warcraft.object.layer.LayerGroup;
import com.google.gson.annotations.JsonAdapter;

/**
 * Instances of this class represent a forest layer. Forests consist of
 * individual cells, trees, which although represented and obtainable as
 * {@link GameObject Items}, are actually drawn by the forest.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ForestAdapter.class)
public class Forest extends LayerGroup
{
    private static final transient float DEFAULT_WOOD = 100;

    public Forest(Skin skin) {
        super(skin);
    }

    @Override
    protected LayerCell createCell(MapLayerEntry entry) {
        return new ForestCell(entry.getPosition(), DEFAULT_WOOD);
    }
}
