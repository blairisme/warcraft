/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.forest;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.layer.LayerGroup;
import com.evilbird.warcraft.object.layer.LayerGroupCell;
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
    public Forest(Skin skin) {
        super(skin);
    }

    @Override
    protected LayerGroupCell createCell(GridPoint2 location) {
        return new ForestCell(this, style, location);
    }

    protected LayerGroupCell createCell(GridPoint2 location, float value) {
        return new ForestCell(this, style, location, value);
    }
}
