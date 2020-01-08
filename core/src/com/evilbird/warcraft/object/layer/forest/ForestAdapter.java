/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.forest;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.object.layer.LayerCell;
import com.evilbird.warcraft.object.layer.LayerGroupAdapter;

/**
 * Instances of this class serialize and deserialize {@link Forest} objects.
 *
 * @author Blair Butterworth
 */
public class ForestAdapter extends LayerGroupAdapter<Forest>
{
    private static final String TREES = "trees";
    private static final String WOOD = "wood";

    @Override
    protected String getCellArrayProperty() {
        return TREES;
    }

    @Override
    protected String getValueProperty() {
        return WOOD;
    }

    @Override
    protected LayerCell createCell(GridPoint2 location, float value) {
        return new ForestCell(location, value);
    }
}
