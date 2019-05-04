/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.spatial;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.collection.Arrays;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.pathing.SpatialNode;
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class represent a single division of the game
 * space, which can be occupied by {@link Item Items}.
 *
 * @author Blair Butterworth
 */
public class ItemNode implements SpatialNode
{
    private int index;
    private Map<Identifier, Item> occupants;
    private GridPoint2 gridReference;
    private transient Vector2 worldReference;
    private transient Array<Connection<ItemNode>> connections;

    public ItemNode(int index, GridPoint2 gridReference) {
        this.index = index;
        this.occupants = new HashMap<>(3);
        this.gridReference = gridReference;
        this.worldReference = new Vector2(32 * gridReference.x, 32 * gridReference.y);
        this.connections = Arrays.emptyArray();
    }

    public Array<Connection<ItemNode>> getConnections() {
        return connections;
    }

    public void setConnections(Array<Connection<ItemNode>> connections) {
        this.connections = connections;
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

    public Collection<Item> getOccupants() {
        return occupants.values();
    }

    public void addOccupant(Item occupant) {
        occupants.put(occupant.getIdentifier(), occupant);
    }

    public void removeOccupant(Item occupant) {
        occupants.remove(occupant.getIdentifier());
    }

    public boolean hasOccupant(Item occupant) {
        return occupants.containsKey(occupant.getIdentifier());
    }

    public void removeOccupants() {
        occupants.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        ItemNode itemNode = (ItemNode)obj;
        return new EqualsBuilder()
            .append(index, itemNode.index)
            .append(occupants, itemNode.occupants)
            .append(gridReference, itemNode.gridReference)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(index)
            .append(occupants)
            .append(gridReference)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("index", index)
            .append("gridReference", gridReference)
            .append("occupants", occupants)
            .toString();
    }
}
