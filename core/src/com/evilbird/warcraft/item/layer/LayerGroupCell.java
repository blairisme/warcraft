/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.item.ItemBasic;
import com.evilbird.engine.item.spatial.ItemGraphOccupant;

import javax.inject.Inject;

/**
 * Represents a single element in a {@link LayerGroup}. Each LayerGroupCell has
 * a value, which when it reaches zero will cause it to ask its parent group to
 * assign it the style and attributes that constitute an empty cell.
 *
 * @author Blair Butterworth
 */
public class LayerGroupCell extends ItemBasic implements ItemGraphOccupant
{
    protected float value;
    protected GridPoint2 location;

    @Inject
    public LayerGroupCell() {
        setTouchable(Touchable.enabled);
        setVisible(false);
    }

    public GridPoint2 getLocation() {
        return location;
    }

    public float getValue() {
        return value;
    }

    public void setLocation(GridPoint2 location) {
        this.location = location;
        setSize(32, 32); //?
        setPosition(location.x * 32, location.y * 32);
    }

    public void setValue(float value) {
        this.value = Math.max(value, 0);
        if (value == 0) {
            setEmpty();
        }
    }

    protected void setEmpty() {
        LayerGroup group = (LayerGroup)getParent();
        group.setEmptyTexture(location);
        setTouchable(Touchable.disabled);
        setType(LayerType.Map);
    }
}
