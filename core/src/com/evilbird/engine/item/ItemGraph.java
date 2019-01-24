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
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.function.AcceptPredicate;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.pathing.SpatialGraph;

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
// TODO: Newly created items are not avoided (not added to spatial graph)
public class ItemGraph implements SpatialGraph<ItemNode>
{
    private boolean populated;
    private float nodeWidth;
    private float nodeHeight;
    private int nodeCountX;
    private int nodeCountY;
    private ItemComposite root;
    private ItemNode[][] nodes;
    private Predicate<ItemNode> connectionFilter;
    private Map<Item, ItemNode> newOccupants;

    public ItemGraph(ItemGraph graph, Predicate<ItemNode> connectionFilter) {
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

    public ItemGraph(ItemComposite root, int nodeWidth, int nodeHeight, int nodeCountX, int nodeCountY) {
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
    public Array<Connection<ItemNode>> getConnections(ItemNode fromNode) {
        Array<Connection<ItemNode>> result = new Array<>();

        GridPoint2 fromIndex = fromNode.getSpatialReference();
        int startX = Math.max(0, fromIndex.x - 1);
        int startY = Math.max(0, fromIndex.y - 1);
        int endX = Math.min(nodeCountX - 1, fromIndex.x + 1);
        int endY = Math.min(nodeCountY - 1, fromIndex.y + 1);

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (!(x == fromIndex.x && y == fromIndex.y)) {
                    ItemNode toNode = nodes[x][y];
                    if (connectionFilter.test(toNode)) {
                        result.add(new ItemConnection(fromNode, toNode));
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

    public ItemNode getNode(Vector2 position) {
        GridPoint2 spatialPosition = toSpatial(position);
        return nodes[spatialPosition.x][spatialPosition.y];
    }

    public Collection<ItemNode> getNodes(Vector2 worldPosition, Vector2 worldSize) {
        GridPoint2 spatialPosition = toSpatial(worldPosition);
        return getNodes(spatialPosition, worldSize);
    }

    public Collection<ItemNode> getNodes(GridPoint2 spatialPosition, Vector2 worldSize) {
        GridPoint2 spatialSize = toSpatial(worldSize);
        return getNodes(spatialPosition, spatialSize);
    }

    public Collection<ItemNode> getNodes(GridPoint2 spatialPosition, GridPoint2 spatialSize) {
        int xCount = spatialSize.x;
        int yCount = spatialSize.y;

        int xStart = spatialPosition.x;
        int yStart = spatialPosition.y;

        int xEnd = Math.min(xStart + xCount, nodeCountX);
        int yEnd = Math.min(yStart + yCount, nodeCountY);

        Collection<ItemNode> result = new ArrayList<>();
        for (int x = xStart; x < xEnd; x++) {
            for (int y = yStart; y < yEnd; y++) {
                result.add(nodes[x][y]);
            }
        }
        return result;
    }

    @Override
    public int getIndex(ItemNode node) {
        return node.getIndex();
    }

    public Map<Item, ItemNode> getNewOccupants() {
        return newOccupants;
    }

    public void update() {
        if (!populated){
            populated = true;
            updateOccupant(root.getItems());
        }
        newOccupants.clear();
    }

    public void addOccupants(ItemNode node, Item occupant) {
        for (ItemNode adjacentNode: getNodes(node.getSpatialReference(), occupant.getSize())) {
            adjacentNode.addOccupant(occupant);
            newOccupants.put(occupant, node);
        }
    }

    public void removeOccupants(ItemNode node, Item occupant) {
        for (ItemNode adjacentNode: getNodes(node.getSpatialReference(), occupant.getSize())) {
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
            for (ItemNode node : getNodes(occupant.getPosition(), occupant.getSize())) {
                node.addOccupant(occupant);
                newOccupants.put(occupant, node);
            }
        }
    }

    private ItemNode[][] createNodeArray(int width, int height) {
        int index = 0;
        ItemNode[][] result = new ItemNode[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                result[x][y] = new ItemNode(index++, new GridPoint2(x, y));
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
