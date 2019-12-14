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
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.layer.LayerCell;
import com.evilbird.warcraft.object.layer.LayerType;
import org.apache.commons.lang3.Validate;

/**
 * Instances of this class represent one cell in the forest layer, decorating
 * it as an {@link GameObject} capable of storing resources, in this case wood.
 *
 * @author Blair Butterworth
 */
public class ForestCell extends LayerCell implements ResourceContainer, SpatialObject
{
    public ForestCell(GridPoint2 location, float value) {
        super(location, value);
        setType(LayerType.Tree);
    }

    @Override
    public float getResource(ResourceType resource) {
        Validate.isTrue(resource == ResourceType.Wood);
        return getValue();
    }

    @Override
    public void setResource(ResourceType resource, float value) {
        Validate.isTrue(resource == ResourceType.Wood);
        setValue(value);
    }

    @Override
    public void setEmpty() {
        super.setEmpty();
        setType(LayerType.Map);
    }
}
