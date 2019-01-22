/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.function.AcceptPredicate;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.warcraft.item.common.capability.Movable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class represent a graph of the game space, represented as
 * a 2 dimensional matrix of 32 x 32 pixel sized nodes.
 *
 * @author Blair Butterworth
 */
// TODO: find all nodes around object and return first one that passes filter.
// TODO: Rename to ItemGraph
// TODO: Refactor common behaviour into engine/common/pathing package
public class SpatialGraph implements IndexedGraph<SpatialItemNode>
{
    private boolean populated;
    private float nodeWidth;
    private float nodeHeight;
    private int nodeCountX;
    private int nodeCountY;
    private ItemComposite root;
    private SpatialItemNode[][] nodes;
    private Predicate<SpatialItemNode> connectionFilter;
    private Map<Item, SpatialItemNode> newOccupants;

    public SpatialGraph(SpatialGraph graph, Predicate<SpatialItemNode> connectionFilter) {
        this.root = graph.root;
        this.nodeWidth = graph.nodeWidth;
        this.nodeHeight = graph.nodeHeight;
        this.nodeCountX = graph.nodeCountX;
        this.nodeCountY = graph.nodeCountY;
        this.nodes = graph.nodes;
        this.populated = graph.populated;
        this.connectionFilter = connectionFilter;
        this.newOccupants = graph.newOccupants;
    }

    public SpatialGraph(ItemComposite root, int nodeWidth, int nodeHeight, int nodeCountX, int nodeCountY) {
        this.root = root;
        this.nodeWidth = nodeWidth;
        this.nodeHeight = nodeHeight;
        this.nodeCountX = nodeCountX;
        this.nodeCountY = nodeCountY;
        this.nodes = createNodeArray(nodeCountX, nodeCountY);
        this.populated = false;
        this.connectionFilter = new AcceptPredicate<>();
        this.newOccupants = new HashMap<>();
    }

    @Override
    public Array<Connection<SpatialItemNode>> getConnections(SpatialItemNode fromNode) {
        Array<Connection<SpatialItemNode>> result = new Array<>();

        GridPoint2 fromIndex = fromNode.getSpatialReference();
        int startX = Math.max(0, fromIndex.x - 1);
        int startY = Math.max(0, fromIndex.y - 1);
        int endX = Math.min(nodeCountX - 1, fromIndex.x + 1);
        int endY = Math.min(nodeCountY - 1, fromIndex.y + 1);

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (!(x == fromIndex.x && y == fromIndex.y)) {
                    SpatialItemNode toNode = nodes[x][y];
                    if (connectionFilter.test(toNode)) {
                        result.add(new SpatialItemConnection(fromNode, toNode));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public int getNodeCount() {
        return nodeCountX * nodeCountY;
    }

    public SpatialItemNode getNode(Vector2 position) {
        GridPoint2 spatialPosition = toSpatial(position);
        return nodes[spatialPosition.x][spatialPosition.y];
    }

    public Collection<SpatialItemNode> getNodes(Vector2 worldPosition, Vector2 worldSize) {
        GridPoint2 spatialPosition = toSpatial(worldPosition);
        return getNodes(spatialPosition, worldSize);
    }

    public Collection<SpatialItemNode> getNodes(GridPoint2 spatialPosition, Vector2 worldSize) {
        GridPoint2 spatialSize = toSpatial(worldSize);
        return getNodes(spatialPosition, spatialSize);
    }

    public Collection<SpatialItemNode> getNodes(GridPoint2 spatialPosition, GridPoint2 spatialSize) {
        int xCount = spatialSize.x;
        int yCount = spatialSize.y;

        int xStart = spatialPosition.x;
        int yStart = spatialPosition.y;

        int xEnd = Math.min(xStart + xCount, nodeCountX);
        int yEnd = Math.min(yStart + yCount, nodeCountY);

        Collection<SpatialItemNode> result = new ArrayList<>();
        for (int x = xStart; x < xEnd; x++) {
            for (int y = yStart; y < yEnd; y++) {
                result.add(nodes[x][y]);
            }
        }
        return result;
    }

    // TODO find all nodes around object and return first one that passes filter
    public SpatialItemNode getAdjacentNode(Vector2 position) {
        SpatialItemNode node = getNode(position);
        Array<Connection<SpatialItemNode>> connections = getConnections(node);

        if (connections.size > 0){
            return connections.get(0).getToNode();
        }
        return node;
    }

    @Override
    public int getIndex(SpatialItemNode node) {
        return node.getIndex();
    }

    public Map<Item, SpatialItemNode> getNewOccupants() {
        return newOccupants;
    }

    public void update() {
        if (!populated){
            populated = true;
            updateOccupant(root.getItems());
        }
        newOccupants.clear();
    }

    public void addOccupants(SpatialItemNode node, Item occupant) {
        for (SpatialItemNode adjacentNode: getNodes(node.getSpatialReference(), occupant.getSize())) {
            adjacentNode.addOccupant(occupant);
            newOccupants.put(occupant, node);
        }
    }

    public void removeOccupants(SpatialItemNode node, Item occupant) {
        for (SpatialItemNode adjacentNode: getNodes(node.getSpatialReference(), occupant.getSize())) {
            adjacentNode.removeOccupant(occupant);
        }
    }

    public void updateOccupant(Collection<Item> occupants) {
        for (Item occupant: occupants) {
            if (occupant instanceof ItemComposite) {
                ItemComposite composite = (ItemComposite)occupant;
                updateOccupant(composite.getItems());
            }
            updateOccupant(occupant);
        }
    }

    public void updateOccupant(Item occupant) {
        if (occupant.getTouchable()) {
            for (SpatialItemNode node : getNodes(occupant.getPosition(), occupant.getSize())) {
                node.addOccupant(occupant);
                newOccupants.put(occupant, node);
            }
        }
    }

    private SpatialItemNode[][] createNodeArray(int width, int height) {
        int index = 0;
        SpatialItemNode[][] result = new SpatialItemNode[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                result[x][y] = new SpatialItemNode(index++, new GridPoint2(x, y));
            }
        }
        return result;
    }

    private GridPoint2 toSpatial(Vector2 vector) {
        GridPoint2 result = new GridPoint2();
        result.x = (int)Math.floor(vector.x / nodeWidth);
        result.y = (int)Math.floor(vector.y / nodeHeight);
        return result;
    }
}
