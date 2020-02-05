/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.spatial;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.collection.Arrays;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.pathing.SpatialNode;
import com.evilbird.engine.object.GameObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class represent a single division of the game
 * space, which can be occupied by {@link GameObject GameObjects}.
 *
 * @author Blair Butterworth
 */
public class GameObjectNode implements SpatialNode
{
    protected int index;
    protected transient Vector2 worldReference;
    protected transient GridPoint2 gridReference;
    protected transient Map<Identifier, GameObject> occupants;
    protected transient Array<Connection<GameObjectNode>> connections;

    public GameObjectNode(int index, GridPoint2 gridReference) {
        this.index = index;
        this.occupants = new HashMap<>(3);
        this.gridReference = gridReference;
        this.worldReference = new Vector2(32 * gridReference.x, 32 * gridReference.y);
        this.connections = Arrays.emptyArray();
    }

    public Array<Connection<GameObjectNode>> getConnections() {
        return connections;
    }

    public void setConnections(Array<Connection<GameObjectNode>> connections) {
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

    public Vector2 getWorldReference(Alignment alignment) {
        if (alignment == Alignment.Center) {
            return new Vector2(worldReference.x + 16, worldReference.y + 16);
        }
        throw new UnsupportedOperationException();
    }

    public Collection<GameObject> getOccupants() {
        return Collections.unmodifiableCollection(occupants.values());
    }

    public boolean hasOccupant(GameObject occupant) {
        return occupants.containsKey(occupant.getIdentifier());
    }

    void addOccupant(GameObject occupant) {
        occupants.put(occupant.getIdentifier(), occupant);
    }

    void removeOccupant(GameObject occupant) {
        occupants.remove(occupant.getIdentifier());
    }

    void removeOccupants() {
        occupants.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        GameObjectNode itemNode = (GameObjectNode)obj;
        return new EqualsBuilder()
            .append(index, itemNode.index)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(index)
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
