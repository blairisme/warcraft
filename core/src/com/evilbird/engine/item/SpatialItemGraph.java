package com.evilbird.engine.item;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SpatialItemGraph implements IndexedGraph<SpatialItemNode>
{
    private int width;
    private int height;
    private SpatialItemNode[][] nodes;

    public SpatialItemGraph(ItemRoot root)
    {
        width = 32;
        height = 32;
        nodes = new SpatialItemNode[width][height];

        int index = 0;
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                Vector2 position = new Vector2(32 * x, 32 * y);
                Item hit = root.hit(position, true);

                GridPoint2 gridRef = new GridPoint2(x,y);
                nodes[x][y] = new SpatialItemNode(index++, gridRef, hit.getType());
            }
        }
    }

    public SpatialItemNode getNode(Vector2 position)
    {
        int x = (int)(position.x / 32);
        int y = (int)(position.y / 32);
        return nodes[x][y];
    }

    @Override
    public int getIndex(SpatialItemNode node)
    {

        return node.getIndex();
    }

    @Override
    public int getNodeCount()
    {
        return width * height;
    }

    @Override
    public Array<Connection<SpatialItemNode>> getConnections(SpatialItemNode fromNode)
    {
        GridPoint2 fromGridRef = fromNode.getSpatialIndex();

        Array<Connection<SpatialItemNode>> result = new Array<Connection<SpatialItemNode>>();

        for (int x = Math.max(0, fromGridRef.x - 1); x <= Math.min(width - 1, fromGridRef.x + 1); x++){
            for (int y = Math.max(0, fromGridRef.y - 1); y <= Math.min(height - 1, fromGridRef.y + 1); y++){

                if (!(x == fromGridRef.x && y == fromGridRef.y))
                {
                    SpatialItemNode toNode = nodes[x][y];
                    Identifier occupant = toNode.getOccupant();

                    if (Objects.equals(occupant, new NamedIdentifier("Map")))
                    {
                        Connection<SpatialItemNode> connection = new DefaultConnection(fromNode, toNode);
                        result.add(connection);
                    }
                }
            }
        }
        return result;
    }
}
