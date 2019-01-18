/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.ai.ManhattanHeuristic;
import com.evilbird.engine.common.ai.SpatialNode;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.*;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.layer.LayerType;

import java.util.Iterator;
import java.util.concurrent.CancellationException;

//TODO: Implement movement capability
//TODO: Orient item towards target at end
//TODO: Don't allow two units to occupy the same node
//TODO: Handle target moving away
public class MoveAction extends BasicAction
{
    private Movable target;



    private Vector2 destination;
    private boolean adjacent;

    private SpatialGraph graph;
    private GraphPath<SpatialItemNode> path;
    private Iterator<SpatialItemNode> pathIterator;
    private int pathIteratorIndex;
    private Vector2 nextNodeLocation;

    public MoveAction(Movable target, Vector2 destination) {
        this(target, destination, false);
    }

    public MoveAction(Movable target, Positionable destination) {
        this(target, destination.getPosition(), true);
    }

    private MoveAction(Movable target, Vector2 destination, boolean adjacent) {
        this.target = target;
        this.destination = destination;
        this.adjacent = adjacent;
    }

    @Override
    public void restart() {
        path = null;
    }

    @Override
    public boolean act(float time) {
        Vector2 position = target.getPosition();
        return !updatePath(position) || updatePosition(position, time);
    }

    private boolean updatePath(Vector2 position) {
        return loadGraph() && loadPath(position) && loadIterator() && incrementPath(position);
    }

    private boolean loadGraph() {
        if (graph == null) {
            ItemRoot root = target.getRoot();
            SpatialGraph rootGraph = root.getSpatialGraph();
            graph = new SpatialGraph(rootGraph, new MoveActionFilter(LayerType.Map)); //TODO: move capability
        }
        return true;
    }

    private boolean loadPath(Vector2 position) {
        if (path == null) {
            SpatialItemNode start = graph.getNode(position);
            SpatialItemNode end = adjacent ? graph.getAdjacentNode(destination) : graph.getNode(destination);
            IndexedAStarPathFinder<SpatialItemNode> pathFinder = new IndexedAStarPathFinder<>(graph);

            Heuristic<SpatialItemNode> heuristic = new ManhattanHeuristic();
            GraphPath<SpatialItemNode> result = new DefaultGraphPath<>();
            if (pathFinder.searchNodePath(start, end, heuristic, result)) {
                path = result;
            }
        }
        return path != null;
    }

    private boolean loadIterator() {
        if (pathIterator == null) {
            pathIteratorIndex = 0;
            pathIterator = path.iterator();
            if (pathIterator.hasNext()) {
                pathIterator.next();
            }
            if (pathIterator.hasNext()) {
                nextNodeLocation = pathIterator.next().getWorldReference();
            }
        }
        return true;
    }

    private boolean incrementPath(Vector2 position) {
        if (position.equals(nextNodeLocation)) {
            //updateOccupants();
            if (pathIterator.hasNext()) {
                SpatialItemNode nextNode = pathIterator.next();
                nextNodeLocation = nextNode.getWorldReference();
                pathIteratorIndex += 1;
            }
            else {
                return false;
            }
        }
        return true;
    }

//    private void updateOccupants() {
//        int itemSize = 2; //TODO: Calculate
//        for (int i = Math.max(0, pathIteratorIndex - itemSize); i <= pathIteratorIndex; i++)
//        {
//            SpatialItemNode node = path.get(i);
//            graph.updateOccupant(node);
//        }
//    }

    private boolean updatePosition(Vector2 oldPosition, float time) {
        Vector2 newPosition = getNextPosition(oldPosition, time);
        target.setPosition(newPosition);
        //return Objects.equals(newPosition, destination);
        return false;
    }

    private Vector2 getNextPosition(Vector2 position, float time) {
        Vector2 remaining = nextNodeLocation.cpy().sub(position);
        float remainingDistance = remaining.len();
        float incrementDistance = time * target.getMovementSpeed();

        if (remainingDistance > incrementDistance) {
            Vector2 direction = remaining.nor();
            Vector2 increment = direction.scl(incrementDistance);
            return position.cpy().add(increment);
        }
        return nextNodeLocation;
    }
}
