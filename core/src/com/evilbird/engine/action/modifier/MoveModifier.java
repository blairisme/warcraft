package com.evilbird.engine.action.modifier;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.ai.ManhattanHeuristic;
import com.evilbird.engine.common.ai.SpatialNode;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.SpatialGraph;
import com.evilbird.engine.item.SpatialItemNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CancellationException;

//TODO: Implement moving around other items.
//TODO: Implement movement restricted to certain terrains. E.g., land, water, air.
public class MoveModifier implements ActionModifier
{
    private ItemRoot root;
    private float speedPerSecond;
    private Vector2 destination;
    private Collection<Identifier> capability;


    private GraphPath<SpatialItemNode> path;
    private Iterator<SpatialItemNode> pathIterator;
    private SpatialItemNode pathNode;


    private boolean update;

    public MoveModifier(Item item, Vector2 destination)
    {
        this.root = item.getRoot();
        this.speedPerSecond = 64f; //TODO: Obtain from item
        this.capability = new ArrayList<Identifier>(); //TODO: Obtain from item
        this.capability.add(new NamedIdentifier("Map")); //TODO: Obtain from item

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
                SpatialGraph rootGraph = root.getSpatialGraph();
                SpatialGraph graph = new SpatialGraph(rootGraph, new NodeWithType(new NamedIdentifier("Map")));


                IndexedAStarPathFinder pathFinder = new IndexedAStarPathFinder(graph);

                SpatialItemNode start = graph.getNode(position);
                SpatialItemNode end = graph.getNode(destination);
                Heuristic<SpatialNode> heuristic = new ManhattanHeuristic(); //new TraversalHeuristic();
                GraphPath<SpatialItemNode> output = new DefaultGraphPath();

                if (pathFinder.searchNodePath(start, end, heuristic, output)){
                    path = output;
                    pathIterator = path.iterator();
                    pathNode = pathIterator.next();
                }
            }

            if (path == null)
            {
                throw new CancellationException();
            }


            GridPoint2 pathIndex = pathNode.getSpatialReference();
            Vector2 pathDestination = new Vector2(32 * pathIndex.x, 32 * pathIndex.y);
            if (position.equals(pathDestination)){

                if (pathIterator.hasNext())
                {
                    pathNode = pathIterator.next();
                }
                else
                {
                    throw new CancellationException();
                }
            }


            Vector2 remaining = pathDestination.cpy().sub(position);
            float remainingDistance = remaining.len();
            float incrementDistance = time * speedPerSecond;

            if (remainingDistance > incrementDistance)
            {
                Vector2 direction = remaining.nor();
                Vector2 increment = direction.scl(incrementDistance);
                Vector2 newPosition = position.cpy().add(increment);
                return newPosition;
            }
            return pathDestination;


        }
        throw new IllegalArgumentException();
    }

    public static class NodeWithType implements Predicate<SpatialItemNode>
    {
        private Identifier type;

        public NodeWithType(Identifier type)
        {
            this.type = type;
        }

        @Override
        public boolean test(SpatialItemNode node)
        {
            Item item = node.getOccupant();
            return type.equals(node != null ? item.getType() : new NamedIdentifier("Unknown"));
        }
    }
}
