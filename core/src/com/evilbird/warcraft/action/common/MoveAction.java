package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.item.Positionable;
import com.evilbird.engine.common.ai.ManhattanHeuristic;
import com.evilbird.engine.common.ai.SpatialNode;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.SpatialGraph;
import com.evilbird.engine.item.SpatialItemNode;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.layer.LayerType;

import java.util.Iterator;
import java.util.concurrent.CancellationException;

//TODO: Implement movement capability
//TODO: Orient item towards target at end
//TODO: Ignore current node when traversing path
//TODO: Don't allow two units to occupy the same node
public class MoveAction extends Action
{
    private Movable target;
    private float speed;
    private Vector2 destination;
    private boolean adjacent;

    private SpatialGraph graph;
    private GraphPath<SpatialItemNode> path;
    private Iterator<SpatialItemNode> pathIterator;
    private int pathIteratorIndex;
    private Vector2 nextNodeLocation;

    public MoveAction(Movable target, Vector2 destination)
    {
        this(target, destination, false);
    }

    public MoveAction(Movable target, Positionable destination)
    {
        this(target, destination.getPosition(), true);
    }

    private MoveAction(Movable target, Vector2 destination, boolean adjacent)
    {
        this.target = target;
        this.speed = target.getMovementSpeed();
        this.destination = destination;
        this.adjacent = adjacent;
    }

    @Override
    public void restart()
    {
        path = null;
    }

    //TODO: Don't use cancellation to signal movement complete
    @Override
    public boolean act(float time)
    {
        try
        {
            Vector2 oldPosition = target.getPosition();
            Vector2 newPosition = getNewPosition(oldPosition, time);

            target.setPosition(newPosition);
            return Objects.equals(newPosition, destination);
        }
        catch (CancellationException e)
        {
            return true;
        }
    }

    private Vector2 getNewPosition(Vector2 position, float time)
    {
        loadPath(position);
        incrementPath(position);
        return updatePosition(position, time);
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
        ItemRoot root = target.getRoot();
        SpatialGraph rootGraph = root.getSpatialGraph();
        return new SpatialGraph(rootGraph, new NodeWithType(LayerType.Map));
    }

    private GraphPath<SpatialItemNode> getSpatialPath(SpatialGraph spatialGraph, Vector2 position)
    {
        SpatialItemNode start = graph.getNode(position);
        SpatialItemNode end = adjacent ? graph.getAdjacentNode(destination) : graph.getNode(destination);
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
