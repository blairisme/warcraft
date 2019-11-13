/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.layer.forest;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.object.layer.LayerGroupAdapter;
import com.evilbird.warcraft.object.layer.LayerGroupCell;

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
    protected LayerGroupCell createCell(GridPoint2 location, float value) {
        return new ForestCell(location, value);
    }
}
