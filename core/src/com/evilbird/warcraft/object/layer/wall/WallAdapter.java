/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.wall;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.object.layer.LayerCell;
import com.evilbird.warcraft.object.layer.LayerGroupAdapter;

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
