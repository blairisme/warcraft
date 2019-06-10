/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.wall;

import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.warcraft.item.layer.LayerGroupCell;
import com.evilbird.warcraft.item.layer.LayerType;

import javax.inject.Inject;

/**
 * Represents a section of a wall, typically spanning one standard tile.
 *
 * @author Blair Butterworth
 */
public class WallSection extends LayerGroupCell implements Destroyable
{
    @Inject
    public WallSection() {
        setType(LayerType.WallSection);
    }

    @Override
    public int getDefence() {
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
}
