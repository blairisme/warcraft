package com.evilbird.engine.action.modifier;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CancellationException;

//TODO: Implement moving around other items.
//TODO: Implement movement restricted to certain terrains. E.g., land, water, air.
public class MoveModifier implements ActionModifier
{
    private Item self;
    private ItemRoot root;
    private float speedPerSecond;
    private float radius;
    private Vector2 destination;
    private Vector2 direction;
    private Collection<Identifier> capability;


    private GraphPath<WorldNode> path;
    private Iterator<WorldNode> pathIterator;
    private WorldNode pathNode;


    private boolean update;

    public MoveModifier(Item item, Vector2 destination)
    {
        this.self = item;
        this.root = item.getRoot();
        this.speedPerSecond = 64f; //TODO: Obtain from item
        this.radius = 36f; //TODO: Obtain from item
        this.capability = new ArrayList<Identifier>(); //TODO: Obtain from item
        this.capability.add(new NamedIdentifier("Map")); //TODO: Obtain from item

        this.direction = null;
        this.destination = destination;
this.update = true;
    }

    @Override
    public void restart()
    {

    }

    @Override
    public Object modify(Object value, float time)
    {
        if (value instanceof Vector2)
        {
            Vector2 position = (Vector2)value;

            if (update)
            {
                update = false;
                IndexedGraph<WorldNode> graph = new WorldGraph(root);
                IndexedAStarPathFinder pathFinder = new IndexedAStarPathFinder(graph);

                WorldNode start = new WorldNode((int)(position.x / 32f), (int)(position.y / 32f), new NamedIdentifier("Peasant"));
                WorldNode end = new WorldNode((int)(destination.x / 32f), (int)(destination.y / 32f), new NamedIdentifier("Map"));
                Heuristic<WorldNode> heuristic = new TraversalHeuristic();
                GraphPath<WorldNode> output = new DefaultGraphPath();

                if (pathFinder.searchNodePath(start, end, heuristic, output)){
                    path = output;
                    pathIterator = path.iterator();
                    pathNode = pathIterator.next();
                    destination = pathNode.getWorldPosition();
                }
            }

            if (path == null)
            {
                throw new CancellationException();
            }



            if (position.equals(destination)){

                if (pathIterator.hasNext())
                {
                    pathNode = pathIterator.next();
                    destination = pathNode.getWorldPosition();
                }
                else
                {
                    throw new CancellationException();
                }
            }


            Vector2 direction = destination.cpy().sub(position).nor();
            Vector2 increment = direction.cpy().scl(time * speedPerSecond);
            Vector2 newPosition = position.cpy().add(increment);

            Rectangle nodeBounds = pathNode.getWorldBounds();
            return nodeBounds.contains(newPosition) ? destination : newPosition;

        }
        throw new IllegalArgumentException();
    }













    private static class WorldNode
    {
        private int x;
        private int y;
        private int index;
        private Identifier occupant;

        public WorldNode(int x, int y, int index, Identifier occupant)
        {
            this.x = x;
            this.y = y;
            this.index = index;
            this.occupant = occupant;
        }

        public int getIndex()
        {
            return index;
        }

        public Identifier getOccupant()
        {
            return occupant;
        }

        public Vector2 getIndexPosition()
        {
            return new Vector2(x, y);
        }

        public Vector2 getWorldPosition()
        {
            return new Vector2(32 * x, 32 * y);
        }

        public Rectangle getWorldBounds()
        {
            return new Rectangle(32 * x, 32 * y, 32, 32);
        }
    }

    private static class WorldNodeConnection implements Connection<WorldNode>
    {
        private WorldNode from;
        private WorldNode to;
        private float cost;

        public WorldNodeConnection(WorldNode from, WorldNode to)
        {
            this.from = from;
            this.to = to;

            Vector2 fromPosition = new Vector2(from.getWorldPosition());
            Vector2 toPosition = new Vector2(to.getWorldPosition());
            this.cost = toPosition.sub(fromPosition).len2();
        }

        @Override
        public float getCost()
        {
            return cost;
        }

        @Override
        public WorldNode getFromNode()
        {
            return from;
        }

        @Override
        public WorldNode getToNode()
        {
            return to;
        }
    }

    private static class WorldGraph implements IndexedGraph<WorldNode>
    {
        private int width;
        private int height;
        private WorldNode[][] nodes;

        public WorldGraph(ItemRoot root)
        {
            width = 32;
            height = 32;
            nodes = new WorldNode[width][height];

            int index = 0;
            for (int x = 0; x < width; x++){
                for (int y = 0; y < height; y++){
                    Vector2 position = new Vector2(32 * x, 32 * y);
                    Item hit = root.hit(position, true);
                    nodes[x][y] = new WorldNode(x, y, index++, hit.getType());
                }
            }
        }

        @Override
        public int getIndex(WorldNode node)
        {
            return node.getIndex();
        }

        @Override
        public int getNodeCount()
        {
            return width * height;
        }

        @Override
        public Array<Connection<WorldNode>> getConnections(WorldNode fromNode)
        {
            int fromX = fromNode.x;
            int fromY = fromNode.y;

            Array<Connection<WorldNode>> result = new Array<Connection<WorldNode>>();

            for (int x = Math.max(0, fromX - 1); x <= Math.min(width - 1, fromX + 1); x++){
                for (int y = Math.max(0, fromY - 1); y <= Math.min(height - 1, fromY + 1); y++){

                    if (!(x == fromX && y == fromY))
                    {
                        WorldNode toNode = nodes[x][y];
                        Identifier occupant = toNode.getOccupant();

                       // if (Objects.equals(occupant, new NamedIdentifier("Map")))
                        {
                            Connection<WorldNode> connection = new DefaultConnection(fromNode, toNode);
                            result.add(connection);
                        }
                    }
                }
            }

            return result;
        }
    }

    private static class TraversalHeuristic implements Heuristic<WorldNode>
    {
        @Override
        public float estimate(WorldNode start, WorldNode end)
        {
            if (start.equals(end)) return 0;

            Vector2 startPosition = new Vector2(start.getWorldPosition());
            Vector2 endPosition = new Vector2(end.getWorldPosition());
            float result = endPosition.sub(startPosition).len();
            return result;
        }
    }

    private static class ManhattanDistance implements Heuristic<WorldNode>
    {
        @Override
        public float estimate(WorldNode node, WorldNode endNode)
        {
            return Math.abs(endNode.x - node.x) + Math.abs(endNode.y - node.y);
        }
    }
}
