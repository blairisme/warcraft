/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.pathing.SpatialNode;

import java.util.ArrayList;
import java.util.List;

public class SpatialItemNode implements SpatialNode
{
    private int index;
    private List<Item> occupants;
    private GridPoint2 gridReference;
    private Vector2 worldReference;

    public SpatialItemNode(int index, GridPoint2 gridReference) {
        this.index = index;
        this.occupants = new ArrayList<>(2);
        this.gridReference = gridReference;
        this.worldReference = new Vector2(32 * gridReference.x, 32 * gridReference.y);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public GridPoint2 getSpatialReference() {
        return gridReference;
    }

    public Vector2 getWorldReference() {
        return worldReference;
    }

    public List<Item> getOccupants() {
        return occupants;
    }

    public void addOccupant(Item occupant) {
        occupants.add(occupant);
    }

    public void removeOccupant(Item occupant) {
        occupants.remove(occupant);
    }
}
