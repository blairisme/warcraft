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
import com.evilbird.engine.item.ItemGroup;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_HEIGHT;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;

/**
 * Represents a single element in a {@link LayerGroup}. Each LayerGroupCell has
 * a value, which when it reaches zero will cause it to ask its parent group to
 * assign it the style and attributes that constitute an empty cell.
 *
 * @author Blair Butterworth
 */
public class LayerGroupCell extends ItemBasic
{
    protected float value;
    protected GridPoint2 location;

    @Inject
    public LayerGroupCell(GridPoint2 location, float value) {
        setTouchable(Touchable.enabled);
        setVisible(false);
        setLocation(location);
        setValue(value);
    }

    public GridPoint2 getLocation() {
        return location;
    }

    public float getValue() {
        return value;
    }

    public void setLocation(GridPoint2 location) {
        this.location = location;
        setSize(TILE_WIDTH, TILE_HEIGHT);
        setPosition(location.x * TILE_WIDTH, location.y * TILE_HEIGHT);
    }

    public void setValue(float value) {
        this.value = Math.max(value, 0);
        reevaluateEmpty();
    }

    @Override
    public void setParent(ItemGroup parent) {
        super.setParent(parent);
        reevaluateEmpty();
    }

    protected void reevaluateEmpty() {
        if (value == 0) {
            setEmpty();
        }
    }

    protected void setEmpty() {
        LayerGroup group = (LayerGroup)getParent();
        if (group != null) {
            group.setEmptyTexture(location);
            group.setAdjacentTextures(location);
            setTouchable(Touchable.disabled);
        }
    }
}
