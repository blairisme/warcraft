/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object.spatial;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.collection.Arrays;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.pathing.SpatialGraph;
import com.evilbird.engine.object.GameObject;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import static com.evilbird.engine.common.collection.Maps.addAll;
import static com.evilbird.engine.common.collection.Maps.removeAll;
import static java.util.Collections.emptyList;

/**
 * Instances of this class represent a graph of the game space, represented as
 * a 2 dimensional matrix of 32 x 32 pixel sized nodes.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(GameObjectGraphSerializer.class)
public class GameObjectGraph implements SpatialGraph<GameObjectNode>
{
    private int nodeWidth;
    private int nodeHeight;
    private int nodeCountX;
    private int nodeCountY;
    private transient Vector2 graphSize;
    private transient Vector2 nodeSize;
    private transient GameObjectNode[][] nodes;
    private transient Predicate<Connection<GameObjectNode>> nodeFilter;
    private transient Map<Identifier, Collection<GameObjectNode>> occupiers;

    public GameObjectGraph(GameObjectGraph graph, Predicate<GameObjectNode> nodeFilter) {
        this.nodes = graph.nodes;
        this.nodeWidth = graph.nodeWidth;
        this.nodeHeight = graph.nodeHeight;
        this.nodeCountX = graph.nodeCountX;
        this.nodeCountY = graph.nodeCountY;
        this.nodeFilter = new ConnectionPredicate(nodeFilter);
        this.nodeSize = new Vector2(nodeWidth, nodeHeight);
        this.graphSize = new Vector2(nodeCountX * nodeWidth, nodeCountY * nodeHeight);
        this.occupiers = graph.occupiers;
    }

    public GameObjectGraph(int nodeWidth, int nodeHeight, int nodeCountX, int nodeCountY) {
        this.nodeWidth = nodeWidth;
        this.nodeHeight = nodeHeight;
        this.nodeCountX = nodeCountX;
        this.nodeCountY = nodeCountY;
        this.nodeFilter = Predicates.accept();
        this.nodes = createNodeArray(nodeCountX, nodeCountY);
        this.nodeSize = new Vector2(nodeWidth, nodeHeight);
        this.graphSize = new Vector2(nodeCountX * nodeWidth, nodeCountY * nodeHeight);
        this.occupiers = new HashMap<>();
    }

    @Override
    public Array<Connection<GameObjectNode>> getConnections(GameObjectNode fromNode) {
        Array<Connection<GameObjectNode>> result = fromNode.getConnections();
        return Arrays.retain(result, nodeFilter);
    }

    @Override
    public int getNodeCount() {
        return nodeCountX * nodeCountY;
    }

    public int getNodeCountX() {
        return nodeCountX;
    }

    public int getNodeCountY() {
        return nodeCountY;
    }

    public int getNodeHeight() {
        return nodeHeight;
    }

    public int getNodeWidth() {
        return nodeWidth;
    }

    public Vector2 getNodeSize() {
        return nodeSize;
    }

    public Vector2 getGraphSize() {
        return graphSize;
    }

    public GameObjectNode getNode(Vector2 worldPosition) {
        return getNode(toSpatial(worldPosition));
    }

    public GameObjectNode getNode(GridPoint2 spatialPosition) {
        return getNode(spatialPosition.x, spatialPosition.y);
    }

    public GameObjectNode getNode(int spatialX, int spatialY) {
        if (spatialX >= 0 && spatialX < nodeCountX && spatialY >= 0 && spatialY < nodeCountY) {
            return nodes[spatialX][spatialY];
        }
        return null;
    }

    public Collection<GameObjectNode> getNodes(GameObject gameObject) {
        return getNodes(gameObject.getPosition(), gameObject.getSize());
    }

    public Collection<GameObjectNode> getNodes(Vector2 worldPosition, Vector2 worldSize) {
        return getNodes(worldPosition, worldSize, 0);
    }

    public Collection<GameObjectNode> getNodes(Vector2 worldPosition, Vector2 worldSize, int worldRadius) {
        GridPoint2 spatialPosition = toSpatial(worldPosition);
        GridPoint2 spatialSize = toSpatial(worldSize);
        int spatialRadius = toSpatial(worldRadius);
        return getNodes(spatialPosition, spatialSize, spatialRadius);
    }

    public Collection<GameObjectNode> getNodes(GridPoint2 spatialPosition, Vector2 worldSize) {
        GridPoint2 spatialSize = toSpatial(worldSize);
        return getNodes(spatialPosition, spatialSize);
    }

    public Collection<GameObjectNode> getNodes(GridPoint2 spatialPosition, GridPoint2 spatialSize) {
        return getNodes(spatialPosition, spatialSize, 0);
    }

    public Collection<GameObjectNode> getNodes(GridPoint2 spatialPosition, GridPoint2 spatialSize, int spatialRadius) {
        int xCount = spatialSize.x + (spatialRadius * 2);
        int yCount = spatialSize.y + (spatialRadius * 2);

        int xStart = spatialPosition.x - spatialRadius;
        int yStart = spatialPosition.y - spatialRadius;

        int xEnd = xStart + xCount;
        int yEnd = yStart + yCount;

        Collection<GameObjectNode> result = new ArrayList<>();
        for (int x = xStart; x < xEnd; x++) {
            for (int y = yStart; y < yEnd; y++) {
                if (x >= 0 && x < nodeCountX && y >= 0 && y < nodeCountY) {
                    result.add(nodes[x][y]);
                }
            }
        }
        return result;
    }

    /**
     * Returns the set of {@link GameObjectNode ItemNodes} directly adjacent to the
     * given {@link GameObject}.
     *
     * @param gameObject  the item whose adjacent nodes will be returned.
     *
     * @return a {@link Collection} of unique {@code ItemNodes}.
     */
    public Collection<GameObjectNode> getAdjacentNodes(GameObject gameObject) {
        return getAdjacentNodes(gameObject.getPosition(), gameObject.getSize());
    }

    /**
     * Returns the set of {@link GameObjectNode ItemNodes} directly adjacent to the
     * given {@link GameObject} and offset by the given radius.
     *
     * @param gameObject          the item whose adjacent nodes will be returned.
     * @param worldRadius   a radius in world terms.
     *
     * @return a {@link Collection} of unique {@code ItemNodes}.
     */
    public Collection<GameObjectNode> getAdjacentNodes(GameObject gameObject, int worldRadius) {
        return getAdjacentNodes(gameObject.getPosition(), gameObject.getSize(), worldRadius);
    }

    /**
     * Returns the set of {@link GameObjectNode ItemNodes} directly adjacent to the
     * given world position and size.
     *
     * @param worldPosition   a position in world terms.
     * @param worldSize       a size in world terms.
     *
     * @return a {@link Collection} of unique {@code ItemNodes}.
     */
    public Collection<GameObjectNode> getAdjacentNodes(Vector2 worldPosition, Vector2 worldSize) {
        return getAdjacentNodes(worldPosition, worldSize, nodeWidth);
    }

    /**
     * Returns the set of {@link GameObjectNode ItemNodes} directly adjacent to the
     * given position, of the given size and offset by the given radius. All
     * values are specified with respect to the world.
     *
     * @param worldPosition a position in world terms.
     * @param worldSize     a size in world terms.
     * @param worldRadius   a radius in world terms.
     *
     * @return a {@link Collection} of unique {@code ItemNodes}.
     */
    public Collection<GameObjectNode> getAdjacentNodes(Vector2 worldPosition, Vector2 worldSize, int worldRadius) {
        Objects.requireNonNull(worldPosition);
        Objects.requireNonNull(worldSize);

        GridPoint2 spatialPosition = toSpatial(worldPosition);
        GridPoint2 spatialSize = toSpatial(worldSize);
        int spatialRadius = worldRadius / nodeWidth;

        return getAdjacentNodes(spatialPosition, spatialSize, spatialRadius);
    }

    /**
     * Returns the set of {@link GameObjectNode ItemNodes} directly adjacent to the
     * given {@link GridPoint2 spatial position} with the given size.
     *
     * @param spatialPosition   the position whose adjacent nodes will be
     *                          returned
     * @param spatialSize       a size that allows adjacent nodes to be
     *                          returned for an object larger than a single
     *                          node.
     *
     * @return a {@link Collection} of unique {@code ItemNodes}.
     */
    public Collection<GameObjectNode> getAdjacentNodes(GridPoint2 spatialPosition, GridPoint2 spatialSize) {
        return getAdjacentNodes(spatialPosition, spatialSize, 1);
    }

    /**
     * Returns the set of {@link GameObjectNode ItemNodes} directly adjacent to the
     * given position, of the given size and offset by the given radius. All
     * values are specified in spatial terms.
     *
     * @param position a position in spatial terms.
     * @param size     a size in spatial terms.
     * @param radius   a radius in spatial terms.
     *
     * @return a {@link Collection} of unique {@code ItemNodes}.
     */
    public Collection<GameObjectNode> getAdjacentNodes(GridPoint2 position, GridPoint2 size, int radius) {
        Objects.requireNonNull(position);
        Objects.requireNonNull(size);

        int xCount = size.x + radius + 1;
        int yCount = size.y + radius + 1;
        int xStart = position.x - radius;
        int yStart = position.y - radius;
        int xEnd = position.x + size.x;
        int yEnd = position.y + size.y;

        Set<GameObjectNode> result = new LinkedHashSet<>();
        result.addAll(getNodeSequence(xStart, 1, yStart, yCount)); //left
        result.addAll(getNodeSequence(xStart, xCount, yStart, 1)); //top
        result.addAll(getNodeSequence(xEnd, 1, yStart, yCount)); //right
        result.addAll(getNodeSequence(xStart, xCount, yEnd, 1)); //bottom
        return result;
    }

    private Collection<GameObjectNode> getNodeSequence(int xStart, int xCount, int yStart, int yCount) {
        Collection<GameObjectNode> result = new ArrayList<>();
        for (int x = xStart; x < xStart + xCount; x++) {
            for (int y = yStart; y < yStart + yCount; y++) {
                if (x >= 0 && x < nodeCountX && y >= 0 && y < nodeCountY) {
                    result.add(nodes[x][y]);
                }
            }
        }
        return result;
    }

    /**
     * Returns the unique index of the given node.
     *
     * @param node  the node whose index will be returned
     * @return      the unique index of the given node.
     */
    @Override
    public int getIndex(GameObjectNode node) {
        return node.getIndex();
    }

    public void addOccupants(Collection<GameObject> occupants) {
        for (GameObject occupant: occupants) {
            addOccupants(occupant);
        }
    }

    public void addOccupants(GameObject occupant) {
        if (occupant instanceof SpatialObject) {
            SpatialObject graphOccupant = (SpatialObject)occupant;
            addOccupants(graphOccupant.getNodes(this), (SpatialObject)occupant);
        }
    }

    public void addOccupants(GameObjectNode node, GameObject occupant) {
        if (occupant instanceof SpatialObject) {
            Collection<GameObjectNode> nodes = getNodes(node.getSpatialReference(), occupant.getSize());
            addOccupants(nodes, (SpatialObject)occupant);
        }
    }

    public void addOccupants(Collection<GameObjectNode> nodes, SpatialObject occupant) {
        for (GameObjectNode node: nodes) {
            node.addOccupant(occupant);
        }
        addAll(occupiers, occupant.getIdentifier(), nodes);
    }

    public void removeOccupants(Collection<GameObject> occupants) {
        for (GameObject occupant: occupants) {
            removeOccupants(occupant);
        }
    }

    public void removeOccupants(GameObject occupant) {
        if (occupant instanceof SpatialObject) {
            SpatialObject graphOccupant = (SpatialObject)occupant;
            removeOccupants(graphOccupant.getNodes(this), (SpatialObject)occupant);
        }
    }

    public void removeOccupants(GameObjectNode node, GameObject occupant) {
        if (occupant instanceof SpatialObject) {
            Collection<GameObjectNode> nodes = getNodes(node.getSpatialReference(), occupant.getSize());
            removeOccupants(nodes, (SpatialObject)occupant);
        }
    }

    public void removeOccupants(Collection<GameObjectNode> nodes, SpatialObject occupant) {
        for (GameObjectNode node: nodes) {
            node.removeOccupant(occupant);
        }
        removeAll(occupiers, occupant.getIdentifier(), nodes);
    }

    public void clearOccupants() {
        for (int x = 0; x < nodeCountX; x++) {
            for (int y = 0; y < nodeCountY; y++) {
                nodes[x][y].removeOccupants();
            }
        }
        occupiers.clear();
    }

    public boolean isPartiallyAligned(GameObject gameObject) {
        Vector2 position = gameObject.getPosition();
        return position.x % nodeWidth != 0 || position.y % nodeHeight != 0;
    }

    public Collection<GameObjectNode> getOccupiedNodes(GameObject gameObject) {
        return Maps.getOrDefault(occupiers, gameObject.getIdentifier(), emptyList());
    }

    public Collection<GameObject> getOccupants(GameObject gameObject) {
        return getOccupants(gameObject, 0);
    }

    public Collection<GameObject> getOccupants(GameObject gameObject, int radius) {
        Collection<GameObject> occupants = new ArrayList<>();
        for (GameObjectNode node: getNodes(gameObject.getPosition(), gameObject.getSize(), radius)) {
            occupants.addAll(node.getOccupants());
        }
        return occupants;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("nodeWidth", nodeWidth)
            .append("nodeHeight", nodeHeight)
            .append("nodeCountX", nodeCountX)
            .append("nodeCountY", nodeCountY)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        GameObjectGraph graph = (GameObjectGraph)obj;
        return new EqualsBuilder()
            .append(nodeWidth, graph.nodeWidth)
            .append(nodeHeight, graph.nodeHeight)
            .append(nodeCountX, graph.nodeCountX)
            .append(nodeCountY, graph.nodeCountY)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(nodeWidth)
            .append(nodeHeight)
            .append(nodeCountX)
            .append(nodeCountY)
            .toHashCode();
    }

    private GameObjectNode[][] createNodeArray(int width, int height) {
        GameObjectNode[][] result = createNodes(width, height);
        addConnections(result, width, height);
        return result;
    }

    private GameObjectNode[][] createNodes(int width, int height) {
        int index = 0;
        GameObjectNode[][] result = new GameObjectNode[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                result[x][y] = new GameObjectNode(index++, new GridPoint2(x, y));
            }
        }
        return result;
    }

    private void addConnections(GameObjectNode[][] nodes, int width, int height) {
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                nodes[x][y].setConnections(createConnections(nodes, nodes[x][y]));
            }
        }
    }

    private Array<Connection<GameObjectNode>> createConnections(GameObjectNode[][] nodes, GameObjectNode fromNode) {
        Array<Connection<GameObjectNode>> result = new Array<>();

        GridPoint2 fromIndex = fromNode.getSpatialReference();
        int startX = Math.max(0, fromIndex.x - 1);
        int startY = Math.max(0, fromIndex.y - 1);
        int endX = Math.min(nodeCountX - 1, fromIndex.x + 1);
        int endY = Math.min(nodeCountY - 1, fromIndex.y + 1);

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (!(x == fromIndex.x && y == fromIndex.y)) {
                    GameObjectNode toNode = nodes[x][y];
                    result.add(new GameObjectConnection(fromNode, toNode));
                }
            }
        }
        return result;
    }

    public GridPoint2 toSpatial(Vector2 vector) {
        GridPoint2 result = new GridPoint2();
        result.x = (int)Math.floor(vector.x / nodeWidth);
        result.y = (int)Math.floor(vector.y / nodeHeight);
        return result;
    }

    private int toSpatial(int value) {
        return value > 0 ? (int)Math.floor(value / nodeWidth) : 0;
    }
}
