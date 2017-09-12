package com.evilbird.engine.action.modifier;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
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

/**
 * @Author Blair Butterworth
 */
//TODO: Use interface to reposition item, not properties
public class MoveModifier implements ActionModifier
{
    private ItemRoot root;
    private float speed;
    private Vector2 destination;
    private Collection<Identifier> capability;
    private boolean useAdjacent;

    private SpatialGraph graph;
    private GraphPath<SpatialItemNode> path;
    private Iterator<SpatialItemNode> pathIterator;
    private int pathIteratorIndex;
    private Vector2 nextNodeLocation;

    public MoveModifier(Item item, Vector2 destination)
    {
        this(item, destination, false);
    }

    public MoveModifier(Item item, Vector2 destination, boolean useAdjacent)
    {
        this.root = item.getRoot();
        this.speed = 64f; //TODO: Obtain from item
        this.capability = new ArrayList<Identifier>(); //TODO: Obtain from item
        this.capability.add(new NamedIdentifier("Map")); //TODO: Obtain from item
        this.destination = destination;
        this.useAdjacent = useAdjacent;
    }

    @Override
    public void restart()
    {
        path = null;
    }

    @Override
    public Object modify(Object value, float time)
    {
        if (value instanceof Vector2)
        {
            Vector2 position = (Vector2)value;
            loadPath(position);
            incrementPath(position);
            return updatePosition(position, time);
        }
        throw new IllegalArgumentException();
    }

    private void loadPath(Vector2 position)
    {
        if (path == null)
        {
            graph = getSpatialGraph();
            path = getSpatialPath(graph, position);
            pathIterator = path.iterator();
            pathIteratorIndex = 0;
            SpatialItemNode nextNode = pathIterator.next();
            nextNodeLocation = nextNode.getWorldReference();
        }
    }

    private SpatialGraph getSpatialGraph()
    {
        SpatialGraph rootGraph = root.getSpatialGraph();
        return new SpatialGraph(rootGraph, new NodeWithType(new NamedIdentifier("Map")));
    }

    private GraphPath<SpatialItemNode> getSpatialPath(SpatialGraph spatialGraph, Vector2 position)
    {
        SpatialItemNode start = graph.getNode(position);
        SpatialItemNode end = useAdjacent ? graph.getAdjacentNode(destination) : graph.getNode(destination);
        IndexedAStarPathFinder pathFinder = new IndexedAStarPathFinder(spatialGraph);
        Heuristic<SpatialNode> heuristic = new ManhattanHeuristic();
        GraphPath<SpatialItemNode> result = new DefaultGraphPath();
        if (pathFinder.searchNodePath(start, end, heuristic, result)){
            return result;
        }
        throw new CancellationException();
    }

    private void incrementPath(Vector2 position)
    {
        if (position.equals(nextNodeLocation)){
            updateOccupants();
            if (pathIterator.hasNext()){
                SpatialItemNode nextNode = pathIterator.next();
                nextNodeLocation = nextNode.getWorldReference();
                pathIteratorIndex += 1;
            }
            else{
                throw new CancellationException();
            }
        }
    }

    private void updateOccupants()
    {
        int itemSize = 2; //TODO: Calculate
        for (int i = Math.max(0, pathIteratorIndex - itemSize); i <= pathIteratorIndex; i++)
        {
            SpatialItemNode node = path.get(i);
            graph.updateOccupant(node);
        }
    }

    private Vector2 updatePosition(Vector2 position, float time)
    {
        Vector2 remaining = nextNodeLocation.cpy().sub(position);
        float remainingDistance = remaining.len();
        float incrementDistance = time * speed;

        if (remainingDistance > incrementDistance)
        {
            Vector2 direction = remaining.nor();
            Vector2 increment = direction.scl(incrementDistance);
            Vector2 newPosition = position.cpy().add(increment);
            return newPosition;
        }
        return nextNodeLocation;
    }

    private static class NodeWithType implements Predicate<SpatialItemNode>
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
            Identifier other = node != null ? item.getType() : new NamedIdentifier("Unknown");
            return type.equals(other);
        }
    }
}
