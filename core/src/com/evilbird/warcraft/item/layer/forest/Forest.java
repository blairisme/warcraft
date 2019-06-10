/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.maps.MapLayerEntry;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.layer.LayerGroup;
import com.evilbird.warcraft.item.layer.LayerGroupCell;
import com.google.gson.annotations.JsonAdapter;

/**
 * Instances of this class represent a forest layer. Forests consist of
 * individual cells, trees, which although represented and obtainable as
 * {@link Item Items}, are actually drawn by the forest.
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
    protected LayerGroupCell createCell(MapLayerEntry entry) {
        ForestCell forestCell = new ForestCell();
        forestCell.setResource(ResourceType.Wood, DEFAULT_WOOD);
        forestCell.setLocation(entry.getPosition());
        return forestCell;
    }
}
