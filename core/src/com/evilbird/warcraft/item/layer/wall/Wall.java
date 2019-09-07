/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.wall;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.layer.LayerGroup;
import com.evilbird.warcraft.item.layer.LayerGroupCell;
import com.google.gson.annotations.JsonAdapter;

/**
 * Instances of this class represent a wall layer. Walls consist of
 * individual cells, wall sections, which although represented and obtainable
 * as {@link Item Items}, are actually drawn by the wall.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(WallAdapter.class)
public class Wall extends LayerGroup
{
    private static final transient float DEFAULT_HEALTH = 100;

    public Wall(Skin skin) {
        super(skin);
    }

    @Override
    protected LayerGroupCell createCell(MapLayerEntry entry) {
        return new WallSection(entry.getPosition(), DEFAULT_HEALTH);
    }
}
