package com.evilbird.engine.item;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.function.AcceptPredicate;
import com.evilbird.engine.common.function.Predicate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SpatialGraph implements IndexedGraph<SpatialItemNode>
{
    private float nodeWidth;
    private float nodeHeight;
    private int nodeCountX;
    private int nodeCountY;
    private ItemComposite root;
    private SpatialItemNode[][] nodes;
    private Map<Item, Collection<SpatialItemNode>> occupantCache;
    private boolean populated;
    private Predicate<SpatialItemNode> connectionFilter;

    public SpatialGraph(SpatialGraph graph, Predicate<SpatialItemNode> connectionFilter)
    {
        this.root = graph.root;
        this.nodeWidth = graph.nodeWidth;
        this.nodeHeight = graph.nodeHeight;
        this.nodeCountX = graph.nodeCountX;
        this.nodeCountY = graph.nodeCountY;
        this.nodes = graph.nodes;
        this.populated = graph.populated;
        this.connectionFilter = connectionFilter;

        this.occupantCache = graph.occupantCache;
    }

    public SpatialGraph(ItemComposite root, int nodeWidth, int nodeHeight, int nodeCountX, int nodeCountY)
    {
        this.root = root;
        this.nodeWidth = nodeWidth;
        this.nodeHeight = nodeHeight;
        this.nodeCountX = nodeCountX;
        this.nodeCountY = nodeCountY;
        this.nodes = createNodeArray(nodeCountX, nodeCountY);
        this.populated = false;
        this.connectionFilter = new AcceptPredicate<SpatialItemNode>();

        this.occupantCache = new HashMap<Item, Collection<SpatialItemNode>>();
    }

    @Override
    public Array<Connection<SpatialItemNode>> getConnections(SpatialItemNode fromNode)
    {
        Array<Connection<SpatialItemNode>> result = new Array<Connection<SpatialItemNode>>();

        GridPoint2 fromIndex = fromNode.getSpatialReference();
        int startX = Math.max(0, fromIndex.x - 1);
        int startY = Math.max(0, fromIndex.y - 1);
        int endX = Math.min(nodeCountX - 1, fromIndex.x + 1);
        int endY = Math.min(nodeCountY - 1, fromIndex.y + 1);

        for (int x = startX; x <= endX; x++){
            for (int y = startY; y <= endY; y++){
                if (!(x == fromIndex.x && y == fromIndex.y)){
                    SpatialItemNode toNode = nodes[x][y];
                    if (connectionFilter.test(toNode)){
                        result.add(new DefaultConnection(fromNode, toNode));
                    }
                }
            }
        }
        return result;
    }

    public SpatialItemNode getNode(Vector2 position)
    {
        int x = (int)Math.floor(position.x / nodeWidth);
        int y = (int)Math.floor(position.y / nodeHeight);
        return nodes[x][y];
    }

    @Override
    public int getNodeCount()
    {
        return nodeCountX * nodeCountY;
    }

    @Override
    public int getIndex(SpatialItemNode node)
    {
        return node.getIndex();
    }

    public void update()
    {
        if (!populated){
            populated = true;
            populate();
        }
    }

    public void populate()
    {
        for (int x = 0; x < nodeCountX; x++) {
            for (int y = 0; y < nodeCountY; y++) {
                updateOccupant(nodes[x][y]);
            }
        }
    }

    public void updateOccupant(SpatialItemNode invalidatedNode)
    {
        Vector2 position = invalidatedNode.getWorldReference();
        Item occupant = root.hit(position, true);
        invalidatedNode.setOccupant(occupant);
    }

    private SpatialItemNode[][] createNodeArray(int width, int height)
    {
        int index = 0;
        SpatialItemNode[][] result = new SpatialItemNode[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                result[x][y] = new SpatialItemNode(index++, new GridPoint2(x, y));
            }
        }
        return result;
    }
}
