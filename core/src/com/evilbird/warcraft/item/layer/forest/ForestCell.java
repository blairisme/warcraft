/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.layer.LayerGroupCell;
import com.evilbird.warcraft.item.layer.LayerType;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this class represent one cell in the forest layer, decorating
 * it as an {@link Item} capable of storing resources, in this case wood.
 *
 * @author Blair Butterworth
 */
public class ForestCell extends LayerGroupCell implements ResourceContainer
{
    @Inject
    public ForestCell() {
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
}
