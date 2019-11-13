/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.path;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.object.common.capability.MovementCapability;
import com.evilbird.warcraft.object.layer.LayerType;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Instances of this class filter the spatial graph, removing those nodes that
 * a given item doesn't have the capability to traverse.
 *
 * @author Blair Butterworth
 */
public class ItemPathFilter implements Predicate<GameObjectNode>
{
    private Collection<GameObject> traversableGameObjects;
    private Collection<Identifier> traversableTypes;

    /**
     * Constructs a new ItemPathFilter that filters all path content.
     */
    public ItemPathFilter() {
        traversableGameObjects = new ArrayList<>();
        traversableTypes = new ArrayList<>();
    }

    /**
     * Specifies that the path to which the filter is applied can contain the
     * given {@link GameObject}.
     *
     * @param gameObject an {@code Item} that can be traversed. This parameter cannot
     *             be {@code null}.
     */
    public void addTraversableItem(GameObject gameObject) {
        Objects.requireNonNull(gameObject);
        traversableGameObjects.add(gameObject);
    }

    /**
     * Specifies that the path to which the filter is applied can contain the
     * given {@link GameObject} {@link Collection}.
     *
     * @param gameObjects a {@code Collection} of {@code Items} that can be traversed.
     *             This parameter cannot be {@code null}.
     */
    public void addTraversableItems(Collection<GameObject> gameObjects) {
        Objects.requireNonNull(gameObjects);
        traversableGameObjects.addAll(gameObjects);
    }

    /**
     * Specifies that the path to which the filter is applied can contain
     * {@link GameObject Items} of the given type.
     *
     * @param type an {@code Item} type. This parameter cannot be {@code null}.
     */
    public void addTraversableType(Identifier type) {
        Objects.requireNonNull(type);
        traversableTypes.add(type);
    }

    /**
     * Specifies that the path to which the filter is applied can contain
     * {@link GameObject Items} of the given types.
     *
     * @param types a {@code Collection} of {@code Item} type. This parameter
     *              cannot be {@code null}.
     */
    public void addTraversableTypes(Collection<Identifier> types) {
        Objects.requireNonNull(types);
        traversableTypes.addAll(types);
    }

    /**
     * Specifies that the path to the filter is applied can contain
     * {@link GameObject Items} that grouped by the given movement capability.
     *
     * @param capability a MovementCapability.
     */
    public void addTraversableCapability(MovementCapability capability) {
        Objects.requireNonNull(capability);
        if (capability == MovementCapability.Land) {
            traversableTypes.add(LayerType.Map);
            traversableTypes.add(LayerType.Shore);
            traversableTypes.add(UnitType.CircleOfPower);
        }
        else if (capability == MovementCapability.ShallowWater) {
            traversableTypes.add(LayerType.Sea);
            traversableTypes.add(LayerType.Shore);
            traversableTypes.add(UnitType.OilPatch);
        }
        else if (capability == MovementCapability.Water) {
            traversableTypes.add(LayerType.Sea);
            traversableTypes.add(UnitType.OilPatch);
        }
        else if (capability == MovementCapability.Air) {
            traversableTypes.addAll(Arrays.asList(LayerType.values()));
            traversableTypes.addAll(Arrays.asList(UnitType.values()));
        }
    }

    @Override
    public boolean test(GameObjectNode node) {
        for (GameObject occupant : node.getOccupants()) {
            if (traversableGameObjects.contains(occupant)) {
                return true;
            }
            if (!traversableTypes.contains(occupant.getType())) {
                return false;
            }
        }
        return true;
    }

    public boolean test(GameObjectNode node, GameObject except) {
        for (GameObject occupant : node.getOccupants()) {
            if (occupant != except && traversableGameObjects.contains(occupant)) {
                return true;
            }
            if (!traversableTypes.contains(occupant.getType())) {
                return false;
            }
        }
        return true;
    }
}
