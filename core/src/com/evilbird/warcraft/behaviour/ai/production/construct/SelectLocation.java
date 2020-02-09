/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.production.construct;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.layer.LayerType;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.UnitSize;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.engine.object.utility.GameObjectComparators.closestItem;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.hasPathTo;
import static com.evilbird.warcraft.object.layer.LayerType.Map;
import static com.evilbird.warcraft.object.layer.LayerType.Shore;
import static com.evilbird.warcraft.object.unit.UnitArchetype.NavalProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.NavalUpgrader;
import static com.evilbird.warcraft.object.unit.UnitArchetype.OilDepot;
import static com.evilbird.warcraft.object.unit.UnitArchetype.OilProducer;

/**
 * A leaf task that selects an unoccupied location in which to build the
 * building contained in the tasks blackboard.
 *
 * @author Blair Butterworth
 */
//TODO: Increase search radius if no free locations found
//TODO: Ensure buildings are not placed next to each other
//TODO: Build lumber mill next to forest
public class SelectLocation extends LeafTask<ConstructData>
{
    private static final transient int SEARCH_RADIUS = 8 * 32;

    @Inject
    public SelectLocation() {
    }

    @Override
    public Status execute() {
        ConstructData data = getObject();
        Vector2 location = getLocation(data);
        data.setLocation(location);
        return location != null ? SUCCEEDED : FAILED;
    }

    private Vector2 getLocation(ConstructData data) {
        UnitArchetype archetype = data.getBuildingArchetype();

        if (isSeaBased(archetype)) {
            return getSeaLocation(data);
        }
        if (isShoreBased(archetype)) {
            return getShoreLocation(data);
        }
        return getLandLocation(data);
    }

    private boolean isSeaBased(UnitArchetype archetype) {
        return archetype == OilProducer;
    }

    private boolean isShoreBased(UnitArchetype archetype) {
        return archetype == NavalProducer
            || archetype == NavalUpgrader
            || archetype == OilDepot;
    }

    private Vector2 getSeaLocation(ConstructData data) {
        GameObject oilPatch = getOilResource(data);
        return getLocation(data, oilPatch);
    }

    private Vector2 getShoreLocation(ConstructData data) {
        GameObject base = getLandBase(data);
        return getAdjacentLocation(data, base, Shore);
    }

    private Vector2 getLandLocation(ConstructData data) {
        GameObject base = getLandBase(data);
        return getAdjacentLocation(data, base, Map);
    }

    private Vector2 getLocation(ConstructData data, GameObject object) {
        if (object != null) {
            GameObjectGraph graph = data.getGraph();
            GameObjectNode node = graph.getNode(object.getPosition());
            return node.getWorldReference();
        }
        return null;
    }

    private Vector2 getAdjacentLocation(ConstructData data, GameObject object, LayerType terrainType) {
        GameObjectGraph graph = data.getGraph();
        UnitType building = data.getBuilding();

        UnitSize size = building.getSize();
        GridPoint2 dimensions = size.getDimensions();
        GridPoint2 spatialSize = graph.toSpatial(dimensions.x, dimensions.y);

        return getAdjacentLocation(graph, object, spatialSize, terrainType);
    }

    private Vector2 getAdjacentLocation(GameObjectGraph graph, GameObject object, GridPoint2 size, LayerType terrain) {
        Collection<GameObjectNode> adjacentNodes = graph.getAdjacentNodes(object, SEARCH_RADIUS);
        List<GameObjectNode> adjacentLocations = Lists.toList(adjacentNodes);

        while (! adjacentLocations.isEmpty()) {
            GameObjectNode location = adjacentLocations.remove(RandomUtils.nextInt(0, adjacentLocations.size()));
            Collection<GameObjectNode> displacement = graph.getNodes(location.getSpatialReference(), size);

            if (isUnoccupied(displacement, terrain)) {
                return location.getWorldReference();
            }
        }
        return null;
    }

    private boolean isUnoccupied(Collection<GameObjectNode> nodes, LayerType terrainType) {
        for (GameObjectNode node: nodes) {
            if (! isUnoccupied(node, terrainType)) {
                return false;
            }
        }
        return true;
    }

    private boolean isUnoccupied(GameObjectNode node, LayerType terrainType) {
        Collection<GameObject> occupants = node.getOccupants();
        if (occupants.size() == 1) {
            GameObject occupant = occupants.iterator().next();
            return Objects.equals(occupant.getType(), terrainType);
        }
        return false;
    }

    private GameObject getLandBase(ConstructData data) {
        GameObject commandCentre = getCommandCentre(data);
        if (commandCentre != null) {
            return commandCentre;
        }
        GameObject goldMine = getGoldMine(data);
        if (goldMine != null) {
            return goldMine;
        }
        return data.getBuilder();
    }

    private GameObject getCommandCentre(ConstructData data) {
        return getGameObject(data.getPlayer(), data.getBuilder(), UnitOperations::isCommandCentre);
    }

    private GameObject getGoldMine(ConstructData data) {
        return getGameObject(data.getNeutralPlayer(), data.getBuilder(), UnitOperations::isGoldResource);
    }

    private GameObject getOilResource(ConstructData data) {
        return getGameObject(data.getNeutralPlayer(), data.getBuilder(), UnitOperations::isOilResource);
    }

    private GameObject getGameObject(Player player, Gatherer builder, Predicate<GameObject> type) {
        Collection<GameObject> objects = player.findAll(type);
        return getClosest(objects, builder);
    }

    private GameObject getClosest(Collection<GameObject> objects, MovableObject source) {
        if (! objects.isEmpty()) {
            List<GameObject> closest = Lists.toList(objects);
            Lists.sort(closest, closestItem(source));
            return CollectionUtils.findFirst(closest, hasPathTo(source));
        }
        return null;
    }

    @Override
    protected Task<ConstructData> copyTo(Task<ConstructData> task) {
        return task;
    }
}
