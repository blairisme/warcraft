/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.query;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.common.path.ItemPathFinder;
import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.SelectableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.not;

/**
 * Defines commonly used {@link Predicate Predicates} that operate on
 * {@link Unit Units}.
 *
 * @author Blair Butterworth
 */
public class UnitPredicates
{
    /**
     * Disable construction of this static helper class.
     */
    private UnitPredicates() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a condition that determines if a path exists between the given
     * objects that conforms to given objects traversal capability.
     */
    public static Predicate<GameObject> hasPathTo(MovableObject source) {
        return gameObject -> ItemPathFinder.hasPath(source, gameObject);
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * {@link ResourceContainer} that contains resources of the given
     * {@link ResourceType resource type}.
     */
    public static Predicate<GameObject> hasResources(ResourceType resourceType) {
        return gameObject -> UnitOperations.hasResources(gameObject, resourceType);
    }

    /**
     * Returns a condition that determines if a given {@link GameObject} belongs to
     * an artificial player, inclusive of the neutral player.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<GameObject> isAi() {
        return UnitOperations::isAi;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is
     * "alive" or not: if the given {@code GameObject} is a
     * {@link PerishableObject} whose health is more than zero.
     */
    public static Predicate<GameObject> isAlive() {
        return UnitOperations::isAlive;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} can
     * do damage to other {@code GameObjects}.
     */
    public static Predicate<GameObject> isAttacker() {
        return UnitOperations::isAttacker;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * building.
     */
    public static Predicate<GameObject> isBuilding() {
        return UnitOperations::isBuilding;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * combatant.
     */
    public static Predicate<GameObject> isCombatant() {
        return UnitOperations::isCombatant;
    }

    /**
     * Returns a condition that determines if a given {@link GameObject} belongs to
     * the user operating the current device.
     */
    public static Predicate<GameObject> isCorporeal() {
        return UnitOperations::isCorporeal;
    }

    /**
     * Returns a condition that determines if a given {@link GameObject} belongs to
     * the corporeal player, the user, or to a {@link Player#isControllable()
     * controllable player}.
     */
    public static Predicate<GameObject> isControllable() {
        return UnitOperations::isControllable;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * {@link Gatherer} that is currently constructing a {@link Building}.
     */
    public static Predicate<GameObject> isConstructing(UnitType building) {
        return object -> UnitOperations.isConstructing(object, building);
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * critter.
     */
    public static Predicate<GameObject> isCritter() {
        return UnitOperations::isCritter;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is
     * "dead" or not: if the given {@code GameObject} is a
     * {@link PerishableObject} whose health is zero.
     */
    public static Predicate<GameObject> isDead() {
        return UnitOperations::isDead;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * building into which can be deposited resources of the given type.
     */
    public static Predicate<GameObject> isDepotFor(ResourceType resource) {
        return gameObject -> UnitOperations.isDepotFor(gameObject, resource);
    }

    /**
     *  Returns a condition that determines if the given {@link GameObject} is
     *  a {@link MovableObject}.
     */
    public static Predicate<GameObject> isMovable() {
        return UnitOperations::isMovable;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * {@link MovableObject} with the capability to move over the given
     * {@link TerrainType terrain}.
     */
    public static Predicate<GameObject> isMovableOver(TerrainType terrainType) {
        return gameObject -> UnitOperations.isMovableOver(gameObject, terrainType);
    }

    /**
     * Returns a condition that determines if a given {@link GameObject} belongs to
     * the neutral user, a special AI player that owns resources and critters.
     */
    public static Predicate<GameObject> isNeutral() {
        return UnitOperations::isNeutral;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * non-combatant.
     */
    public static Predicate<GameObject> isNonCombatant() {
        return not(isCombatant());
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * {@link SelectableObject} that is currently
     * {@link SelectableObject#getSelected() selected}.
     */
    public static Predicate<GameObject> isSelected() {
        return UnitOperations::isSelected;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is
     * {@link SelectableObject selectable}.
     */
    public static Predicate<GameObject> isSelectable() {
        return UnitOperations::isSelectable;
    }
}
