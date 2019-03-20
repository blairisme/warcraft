/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemBasic;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.resource.ResourceType;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this class represent one cell in the forest layer, decorating
 * it as an {@link Item} capable of storing resources, in this case wood.
 *
 * @author Blair Butterworth
 */
public class ForestCell extends ItemBasic implements ResourceContainer
{
    private float wood;
    private GridPoint2 location;

    @Inject
    public ForestCell() {
        setType(LayerType.Tree);
        setTouchable(Touchable.enabled);
        setSelectable(false);
        setSelected(false);
        setVisible(false);
    }

    public GridPoint2 getLocation() {
        return location;
    }

    @Override
    public float getResource(ResourceIdentifier resource) {
        Validate.isTrue(resource == ResourceType.Wood);
        return wood;
    }

    public float getWood() {
        return wood;
    }

    public void setLocation(GridPoint2 location) {
        this.location = location;
        setSize(32, 32);
        setPosition(location.x * 32, location.y * 32);
    }

    @Override
    public void setResource(ResourceIdentifier resource, float value) {
        Validate.isTrue(resource == ResourceType.Wood);
        wood = Math.max(value, 0);
        if (wood == 0) {
            Forest forest = (Forest)getParent();
            forest.setDeadTexture(location);
        }
    }

    public void setWood(float wood) {
        setResource(ResourceType.Wood, wood);
    }
}
