/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.layer.wall;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.layer.LayerGroup;
import com.evilbird.warcraft.object.layer.LayerGroupCell;
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
    protected LayerGroupCell createCell(MapLayerEntry entry) {
        return new WallSection(entry.getPosition());
    }
}
