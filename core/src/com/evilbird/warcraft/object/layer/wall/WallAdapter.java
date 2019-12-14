/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.layer.wall;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.object.layer.LayerGroupAdapter;
import com.evilbird.warcraft.object.layer.LayerCell;

/**
 * Instances of this class serialize and deserialize {@link Wall Walls}.
 *
 * @author Blair Butterworth
 */
public class WallAdapter extends LayerGroupAdapter<Wall>
{
    private static final String WALLS = "walls";
    private static final String HEALTH = "health";

    @Override
    protected String getCellArrayProperty() {
        return WALLS;
    }

    @Override
    protected String getValueProperty() {
        return HEALTH;
    }

    @Override
    protected LayerCell createCell(GridPoint2 location, float value) {
        return new WallSection(location, value);
    }
}
