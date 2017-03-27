package com.evilbird.engine.item;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.function.AcceptPredicate;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
            populate();
        }
        else{
            maintain();
        }
    }

    private void populate()
    {
        populated = true;
        for (int x = 0; x < nodeCountX; x++) {
            for (int y = 0; y < nodeCountY; y++) {
                setOccupant(nodes[x][y]);
            }
        }
    }

    private void maintain()
    {
        Collection<Item> modified = root.findAll(itemWithAction());
        for (Item item: modified)
        {
            Collection<SpatialItemNode> invalidated = getSpatialNodes(item);
            //Collection<SpatialItemNode> cached = getCachedOccupants(item);

            setOccupants(invalidated);
            //setOccupants(cached);
        }
    }

    private Collection<SpatialItemNode> getSpatialNodes(Item item)
    {
        Vector2 position = item.getPosition();
        int startX = (int)(position.x / nodeWidth) - 1;
        int startY = (int)(position.y / nodeHeight) - 1;

        Vector2 size = item.getSize();
        int endX = startX + (int)(size.x / nodeWidth) + 1;
        int endY = startY + (int)(size.x / nodeWidth) + 1;

        Collection<SpatialItemNode> result = new ArrayList<SpatialItemNode>();
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                result.add(nodes[x][y]);
            }
        }
        return result;
    }

    private void setOccupants(Collection<SpatialItemNode> invalidatedNodes)
    {
        for (SpatialItemNode invalidatedNode: invalidatedNodes){
            setOccupant(invalidatedNode);
        }
    }

    private void setOccupant(SpatialItemNode invalidatedNode)
    {
        GridPoint2 gridRef = invalidatedNode.getSpatialReference();
        Vector2 position = new Vector2((gridRef.x * nodeWidth) + 16, (gridRef.y * nodeHeight) + 16);

        Item occupant = root.hit(position, true);
        Item previous = invalidatedNode.getOccupant();
        invalidatedNode.setOccupant(occupant);

        if (!Objects.equals(occupant,previous ))
        {
            String type = occupant != null ? occupant.getType().toString() : "<Null>";
            System.out.println("Updated x:" + String.valueOf(gridRef.x) + " y:" + String.valueOf(gridRef.y) + " t:" + type);
        }
    }

    private Collection<SpatialItemNode> getCachedOccupants(Item item)
    {
        Collection<SpatialItemNode> result = new ArrayList<SpatialItemNode>();
        for (int x = 0; x < nodeCountX; x++){
            for (int y = 0; y < nodeCountY; y++){
                SpatialItemNode node = nodes[x][y];
                if (node.equals(item)){
                    result.add(node);
                }
            }
        }
        return result;
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
