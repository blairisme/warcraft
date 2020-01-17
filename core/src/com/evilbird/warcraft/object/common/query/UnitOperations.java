/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.query;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.OffensiveCapability;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.SelectableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.selector.Selector;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.CombatantVessel;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnit;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;
import com.evilbird.warcraft.object.unit.combatant.naval.Ship;
import com.evilbird.warcraft.object.unit.combatant.naval.Submarine;
import com.evilbird.warcraft.object.unit.combatant.naval.Transport;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;
import com.evilbird.warcraft.object.unit.critter.Critter;
import com.evilbird.warcraft.object.unit.resource.Resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static com.evilbird.engine.common.collection.CollectionUtils.findFirst;
import static com.evilbird.engine.object.utility.GameObjectComparators.closestItem;
import static com.evilbird.engine.object.utility.GameObjectOperations.findAncestor;
import static com.evilbird.engine.object.utility.GameObjectOperations.isNear;
import static com.evilbird.engine.object.utility.GameObjectPredicates.hasType;
import static com.evilbird.engine.object.utility.GameObjectPredicates.touchableWithType;
import static com.evilbird.warcraft.data.resource.ResourceType.Gold;
import static com.evilbird.warcraft.data.resource.ResourceType.Oil;
import static com.evilbird.warcraft.data.resource.ResourceType.Wood;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.hasPathTo;
import static com.evilbird.warcraft.object.unit.UnitArchetype.CommandCentre;
import static com.evilbird.warcraft.object.unit.UnitArchetype.FoodProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.NavalProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.OilDepot;
import static com.evilbird.warcraft.object.unit.UnitArchetype.WoodDepot;

/**
 * Instances of this class contain common operations for working with Items.
 *
 * @author Blair Butterworth
 */
public class UnitOperations
{
    /**
     * Disable construction of this static helper class.
     */
    private UnitOperations() {
    }

    /**
     * Returns the {@link GameObject} closest to the given target that is both
     * touchable and of the given type, if any.
     */
    public static GameObject findClosest(MovableObject target, Identifier type) {
        return findClosest(target, target, touchableWithType(type));
    }

    /**
     * Returns the {@link GameObject} closest to the given locus that is both
     * touchable and of the given type, if any.
     */
    public static GameObject findClosest(MovableObject movable, GameObject locus, Identifier type) {
        return findClosest(movable, locus, touchableWithType(type));
    }

    /**
     * Returns the {@link GameObject} closest to the given locus that conforms
     * to the condition.
     */
    public static GameObject findClosest(MovableObject movable, Predicate<GameObject> applicability) {
        return findClosest(movable, movable, applicability);
    }

    /**
     * Returns the {@link GameObject} closest to the given locus that conforms
     * to the given condition.
     */
    public static GameObject findClosest(MovableObject source, GameObject locus, Predicate<GameObject> applicability) {
        GameObjectComposite group = source.getRoot();
        Collection<GameObject> gameObjects = group.findAll(applicability);

        if (! gameObjects.isEmpty()) {
            List<GameObject> closest = new ArrayList<>(gameObjects);
            Lists.sort(closest, closestItem(locus));
            return findFirst(closest, hasPathTo(source));
        }
        return null;
    }

    /**
     * Returns a {@link Collection} containing all of the
     * {@link Player#isArtificial() artifical players} owned by the given
     * {@link GameObjectContainer container}, if any.
     */
    public static Collection<Player> getArtificialPlayers(GameObjectContainer container) {
        Objects.requireNonNull(container);
        Collection<Player> players = new ArrayList<>();
        for (Player player: getPlayers(container)) {
            if (player.isArtificial()) {
                players.add(player);
            }
        }
        return players;
    }

    /**
     * Returns the {@link Player#isCorporeal() corporeal player} contained int the
     * given {@link GameObjectContainer container}, if any.
     */
    public static Player getCorporealPlayer(GameObjectContainer container) {
        Objects.requireNonNull(container);
        for (Player player: getPlayers(container)) {
            if (player.isCorporeal()) {
                return player;
            }
        }
        return null;
    }

    /**
     * Returns the {@link Player} that the given {@link GameObject} belongs to, if
     * any (Terrain items, for example, aren't owned by a player).
     */
    public static Player getPlayer(GameObject gameObject) {
        GameObjectGroup parent = gameObject.getParent();

        if (isPlayer(parent)) {
            return (Player)parent;
        }
        if (isPlayer(gameObject)) {
            return (Player)gameObject;
        }
//        if (gameObject instanceof Unit) {
//            Unit unit = (Unit)gameObject;
//            return unit.getTeam();
//        }
        return (Player)findAncestor(gameObject, UnitOperations::isPlayer);
    }

    /**
     * Returns a {@link Collection} of all {@link Player Players} contained in
     * the given {@link GameObjectContainer object container}.
     */
    public static Collection<Player> getPlayers(GameObjectContainer container) {
        Objects.requireNonNull(container);
        Collection<Player> players = new ArrayList<>();
        for (GameObject gameObject : container.getObjects()) {
            if (gameObject instanceof Player) {
                players.add((Player)gameObject);
            }
        }
        return players;
    }

    /**
     * Returns a {@link Collection} of {@link Player Players} contained in the
     * given {@link GameObjectContainer} that are {@link Player#isViewable()
     * viewable}.
     */
    public static Collection<Player> getViewablePlayers(GameObjectContainer state) {
        Collection<Player> players = new ArrayList<>();
        for (Player player: getPlayers(state)) {
            if (player.isViewable()) {
                players.add(player);
            }
        }
        return players;
    }

    /**
     * Determines if the given {@link GameObject} is a {@link ResourceContainer}
     * that contains resources of the given {@link ResourceType resource type}.
     */
    public static boolean hasResources(GameObject gameObject, ResourceType type) {
        if (gameObject instanceof ResourceContainer) {
            ResourceContainer container = (ResourceContainer)gameObject;
            return container.getResource(type) > 0;
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a {@link Unit} that has
     * been assigned the given {@link Selector}.
     */
    public static boolean hasSelector(GameObject object) {
        if (object instanceof Unit) {
            Unit unit = (Unit)object;
            return unit.getSelector() != null;
        }
        return false;
    }

    /**
     * Determines if given {@link Player} owns any units of the given
     * {@link UnitType type}.
     */
    public static boolean hasUnit(Player player, UnitType type) {
        return GameObjectOperations.hasAny(player, hasType(type));
    }

    /**
     * Determines if given {@link Player} owns units with all of the given
     * {@link UnitType types}.
     */
    public static boolean hasUnits(Player player, UnitType ... types) {
        for (UnitType type: types) {
            if (! hasUnit(player, type)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines if the given {@link GameObject} is "alive" or not: if the
     * given {@code GameObject} is a {@link PerishableObject} whose health is
     * more than zero.
     */
    public static boolean isAlive(GameObject gameObject) {
        if (gameObject instanceof PerishableObject) {
            PerishableObject perishable = (PerishableObject)gameObject;
            return perishable.isAlive();
        }
        return false;
    }

    /**
     * Determines if a given {@link GameObject} belongs to an artificial player,
     * inclusive of the neutral player.
     */
    public static boolean isAi(GameObject gameObject) {
        if (gameObject != null) {
            Player player = UnitOperations.getPlayer(gameObject);
            return player != null && !player.isCorporeal();
        }
        return false;
    }

    /**
     * Determines if a given {@link GameObject} belongs to an artificial player,
     * excluding the neutral player.
     */
    public static boolean isArtificial(GameObject gameObject) {
        if (gameObject != null) {
            Player player = UnitOperations.getPlayer(gameObject);
            return player != null && player.isArtificial();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} can do damage to other
     * {@code GameObjects}.
     */
    public static boolean isAttacker(GameObject gameObject) {
        if (gameObject instanceof OffensiveObject) {
            OffensiveObject combatant = (OffensiveObject) gameObject;
            return combatant.getAttackCapability() != OffensiveCapability.None;
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a {@link Building}: a
     * {@link Unit} specialization that provides the user the ability to train
     * {@link Unit Units} and research {@link Upgrade Upgrades}.
     */
    public static boolean isBuilding(GameObject gameObject) {
        return gameObject instanceof Building;
    }

    /**
     * Determines if the given {@link GameObject} is a {@link SpellCaster} that
     * is currently casting the given {@link Spell}.
     */
    public static boolean isCastingSpell(GameObject gameObject, Spell spell) {
        if (gameObject instanceof SpellCaster) {
            SpellCaster spellCaster = (SpellCaster) gameObject;
            return spellCaster.getCastingSpell() == spell;
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a combatant.
     */
    public static boolean isCombatant(GameObject gameObject) {
        return gameObject instanceof Combatant;
    }

    /**
     * Determines if the given {@link GameObject} is a {@link Gatherer} that is
     * currently constructing a {@link Building}.
     */
    public static boolean isConstructing(GameObject object, UnitType type) {
        if (object instanceof Gatherer) {
            Gatherer gatherer = (Gatherer)object;
            Building building = gatherer.getConstruction();
            return building != null && building.getType() == type;
        }
        return false;
    }

    /**
     * Determines if a given {@link GameObject} belongs to the corporeal player, the
     * user, or to a {@link Player#isControllable() controllable player}.
     */
    public static boolean isControllable(GameObject gameObject) {
        if (gameObject != null) {
            Player player = UnitOperations.getPlayer(gameObject);
            return player != null && (player.isCorporeal() || player.isControllable());
        }
        return false;
    }

    /**
     * Determines if a given {@link GameObject} belongs to the user operating the
     * current device.
     */
    public static boolean isCorporeal(GameObject gameObject) {
        if (gameObject != null) {
            Player player = UnitOperations.getPlayer(gameObject);
            return player != null && player.isCorporeal();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a critter: an animal that
     * inhabits the game world.
     */
    public static boolean isCritter(GameObject gameObject) {
        return gameObject instanceof Critter;
    }

    /**
     * Determines if the given {@link GameObject} is "dead" or not: if the
     * {@code GameObject} is a {@link PerishableObject} whose health is zero.
     */
    public static boolean isDead(GameObject gameObject) {
        if (gameObject instanceof PerishableObject) {
            PerishableObject perishable = (PerishableObject)gameObject;
            return perishable.isDead();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a building into which can
     * be deposited resources of the given type.
     */
    public static boolean isDepotFor(GameObject gameObject, ResourceType resource) {
        if (gameObject instanceof Building) {
            UnitType type = (UnitType)gameObject.getType();
            UnitArchetype archetype = type.getArchetype();
            return (resource == Gold && archetype == CommandCentre)
                || (resource == Oil && (archetype == NavalProducer || archetype == OilDepot))
                || (resource == Wood && (archetype == CommandCentre || archetype == WoodDepot));
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} can be destroyed.
     */
    public static boolean isDestroyable(GameObject gameObject) {
        return gameObject instanceof PerishableObject;
    }

    /**
     * Determines if the given {@link GameObject} produces food.
     */
    public static boolean isFoodProducer(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            Unit unit = (Unit) gameObject;
            UnitType type = (UnitType)unit.getType();
            UnitArchetype archetype = type.getArchetype();
            return archetype == FoodProducer;
        }
        return false;
    }

    /**
     * Returns where the given {@link GameObject} is a flying unit, a
     * {@link CombatantVessel} specialization representing a unit that flies.
     */
    public static boolean isFlying(GameObject gameObject) {
        return gameObject instanceof FlyingUnit;
    }

    /**
     * Returns where the given {@link GameObject} is a gatherer: a
     * {@link Combatant} specialization that can fight, collect resources and
     * construct {@link Building Buildings}.
     */
    public static boolean isGatherer(GameObject gameObject) {
        return gameObject instanceof Gatherer;
    }

    /**
     * Returns whether or not the given {@link GameObject} is currently
     * highlighted.
     */
    public static boolean isHighlighted(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            Unit unit = (Unit) gameObject;
            return unit.getHighlighted();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a {@link MovableObject}.
     */
    public static boolean isMovable(GameObject gameObject) {
        return gameObject instanceof MovableObject;
    }

    /**
     * Determines if the given {@link GameObject} is a {@link MovableObject}
     * with the capability to move over the given {@link TerrainType terrain}.
     */
    public static boolean isMovableOver(GameObject gameObject, TerrainType terrainType) {
        if (gameObject instanceof MovableObject) {
            MovableObject movable = (MovableObject)gameObject;
            return terrainType.equals(movable.getMovementCapability());
        }
        return false;
    }

    /**
     * Determines if a given {@link GameObject} belongs to the neutral player,
     * a special AI player that owns resources and critters.
     */
    public static boolean isNeutral(GameObject gameObject) {
        if (gameObject != null) {
            Player player = UnitOperations.getPlayer(gameObject);
            return player != null && player.isNeutral();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a {@link Player}: a game
     * object owner.
     */
    public static boolean isPlayer(GameObject gameObject) {
        return gameObject instanceof Player;
    }

    /**
     * Determines if the given {@link OffensiveObject combatant} is within
     * attack range of the given {@link GameObject target}.
     */
    public static boolean inRange(OffensiveObject combatant, GameObject target) {
        return isNear(combatant, combatant.getAttackRange(), target);
    }

    /**
     * Determines if the given {@link GameObject} is a {@link Resource}: a game
     * object that contains provides resources.
     */
    public static boolean isResource(GameObject gameObject) {
        return gameObject instanceof Resource;
    }

    /**
     * Determines if the given {@link GameObject} is a {@link SelectableObject}
     * that is currently {@link SelectableObject#getSelected()}.
     */
    public static boolean isSelected(GameObject gameObject) {
        if (gameObject instanceof SelectableObject) {
            SelectableObject selectable = (SelectableObject)gameObject;
            return selectable.getSelected();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is {@link SelectableObject
     * selectable}.
     */
    public static boolean isSelectable(GameObject gameObject) {
        return gameObject instanceof SelectableObject;
    }

    /**
     * Determines if the given {@link GameObject} is a {@link Selector}: a
     * visual guide used by the user to select items and locations in the game
     * world.
     */
    public static boolean isSelector(GameObject gameObject) {
        return gameObject instanceof Selector;
    }

    /**
     * Determines if the given {@link GameObject} is a ship: a
     * {@link RangedCombatant} specialization that can traverse and attack
     * {@code GameObjects} objects on water.
     */
    public static boolean isShip(GameObject gameObject) {
        return gameObject instanceof Ship;
    }

    /**
     * Determines if the given {@link GameObject} is a submarine: a
     * {@link RangedCombatant} specialization that can traverse and attack
     * {@code GameObjects} objects under water.
     */
    public static boolean isSubmarine(GameObject gameObject) {
        return gameObject instanceof Submarine;
    }

    /**
     * Returns whether the given {@link GameObject} is a transport: a
     * {@link Ship} specialization that transports land {@link Unit Units}
     * across water.
     */
    public static boolean isTransport(GameObject gameObject) {
        return gameObject instanceof Transport;
    }

    /**
     * Determines if the given {@link GameObject} is a {@link Building} that is
     * currently under construction.
     */
    public static boolean isUnderConstruction(GameObject object) {
        if (object instanceof Building) {
            Building building = (Building)object;
            return building.isConstructing();
        }
        return false;
    }

    /**
     * Repositions the given {@link MovableObject} so that its placed adjacent
     * to the given {@link GameObject target}.
     */
    public static void moveAdjacent(MovableObject subject, GameObject target) {
        GameObjectContainer root = target.getRoot();
        GameObjectGraph graph = root.getSpatialGraph();

        ItemPathFilter capability = new ItemPathFilter();
        capability.addTraversableCapability(subject.getMovementCapability());

        Collection<GameObjectNode> adjacent = graph.getAdjacentNodes(target.getPosition(), target.getSize());
        GameObjectNode unoccupied = CollectionUtils.findFirst(adjacent, capability);

        if (unoccupied != null) {
            graph.removeOccupants(subject);
            subject.setPosition(unoccupied.getWorldReference());
            graph.addOccupants(subject);
        }
    }

    /**
     * Changes the orientation of the given {@link MovableObject} object so
     * that it faces the given {@link GameObject target}.
     */
    public static void reorient(MovableObject subject, GameObject target, boolean perpendicular) {
        Vector2 itemPosition = subject.getPosition();
        Vector2 targetPosition = target.getPosition();
        Vector2 direction = targetPosition.sub(itemPosition);
        Vector2 normalizedDirection = direction.nor();
        Vector2 newDirection = normalizedDirection.rotate(perpendicular ? 90 : 0);
        subject.setDirection(newDirection);
    }

    /**
     * Changes the orientation of the given {@link GameObject object}, provided
     * it implements {@link MovableObject}, so that it faces the given
     * {@link GameObject target}.
     */
    public static void reorient(GameObject subject, GameObject target, boolean perpendicular) {
        if (subject instanceof MovableObject) {
            reorient((MovableObject)subject, target, perpendicular);
        }
    }
}
