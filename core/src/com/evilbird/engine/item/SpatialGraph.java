package com.evilbird.engine.item;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.function.AcceptPredicate;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;

import java.util.Collection;

import static com.evilbird.engine.item.ItemPredicates.itemWithAction;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SpatialGraph implements IndexedGraph<SpatialItemNode>
{
    private int nodeWidth;
    private int nodeHeight;
    private int nodeCountX;
    private int nodeCountY;
    private ItemComposite root;
    private SpatialItemNode[][] nodes;
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
    }

    @Override
    public Array<Connection<SpatialItemNode>> getConnections(SpatialItemNode fromNode)
    {
        Array<Connection<SpatialItemNode>> result = new Array<Connection<SpatialItemNode>>();

        GridPoint2 fromIndex = fromNode.getSpatialIndex();
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
        int x = (int)(position.x / nodeWidth);
        int y = (int)(position.y / nodeHeight);
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
            updateAll();
        }
        else{
            //updateModified();
        }
    }

    private void updateModified()
    {
        Collection<Item> modified = root.findAll(itemWithAction());
        for (Item item: modified)
        {
            Vector2 position = item.getPosition();
            Vector2 size = item.getSize();

            int startX = (int)(position.x / nodeWidth);
            int startY = (int)(position.y / nodeHeight);
            int endX = startX + (int)(size.x / nodeWidth);
            int endY = startY + (int)(size.x / nodeWidth);

            for (int x = startX; x < endX; x++) {
                for (int y = startY; y < endY; y++) {
                    update(x, y);
                }
            }
        }
    }

    private void updateAll()
    {
        for (int x = 0; x < nodeCountX; x++) {
            for (int y = 0; y < nodeCountY; y++) {
                update(x, y);
            }
        }
    }

    private void update(int x, int y)
    {
        Vector2 position = new Vector2(x * nodeWidth, y * nodeHeight);
        Item hit = root.hit(position, true);
        nodes[x][y].setOccupant(hit != null ? hit.getType() : new NamedIdentifier("Unknown"));
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
