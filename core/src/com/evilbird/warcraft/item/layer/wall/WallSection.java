/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.wall;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.item.spatial.ItemGraphOccupant;
import com.evilbird.warcraft.item.layer.LayerGroupCell;
import com.evilbird.warcraft.item.layer.LayerType;

/**
 * Represents a section of a wall, typically spanning one standard tile.
 *
 * @author Blair Butterworth
 */
public class WallSection extends LayerGroupCell implements Destroyable, ItemGraphOccupant
{
    public WallSection(GridPoint2 location, float value) {
        super(location, value);
        setType(LayerType.WallSection);
    }

    @Override
    public int getArmour() {
        return 0;
    }

    @Override
    public float getHealth() {
        return getValue();
    }

    @Override
    public void setHealth(float health) {
        setValue(health);
    }

    @Override
    public void setEmpty() {
        super.setEmpty();
        setType(LayerType.Map);
    }
}
