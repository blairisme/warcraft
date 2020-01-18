/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.wall;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.layer.LayerCell;
import com.evilbird.warcraft.object.layer.LayerGroup;
import com.google.gson.annotations.JsonAdapter;

/**
 * Instances of this class represent a wall layer. Walls consist of
 * individual cells, wall sections, which although represented and obtainable
 * as {@link GameObject Items}, are actually drawn by the wall.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(WallAdapter.class)
public class Wall extends LayerGroup
{
    public Wall(Skin skin) {
        super(skin);
    }

    @Override
    protected LayerCell createCell(GridPoint2 location) {
        return new WallSection(style, location);
    }

    protected LayerCell createCell(GridPoint2 location, float value) {
        return new WallSection(style, location);
    }
}
