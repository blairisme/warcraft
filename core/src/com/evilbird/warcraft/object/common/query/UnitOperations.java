/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.query;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.resource.ResourceContainer;
import com.evilbird.warcraft.object.common.resource.ResourceType;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.common.upgrade.Upgrade;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.selector.building.BuildingSelector;
import com.evilbird.warcraft.object.selector.target.TargetSelector;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.naval.Submarine;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;
import com.evilbird.warcraft.object.unit.critter.Critter;
import com.evilbird.warcraft.object.unit.resource.Resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import static com.evilbird.engine.common.collection.CollectionUtils.findFirst;
import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.object.utility.GameObjectComparators.closestItem;
import static com.evilbird.engine.object.utility.GameObjectOperations.findAncestor;
import static com.evilbird.engine.object.utility.GameObjectOperations.isNear;
import static com.evilbird.engine.object.utility.GameObjectPredicates.hasType;
import static com.evilbird.engine.object.utility.GameObjectPredicates.touchableWithType;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.hasPathTo;

/**
 * Instances of this class contain common operations for working with Items.
 *
 * @author Blair Butterworth
 */
public class UnitOperations
{
    /**
     * Disable construction of static helper class.
     */
    private UnitOperations() {
    }

    /**
     * Returns the {@link GameObject} closest to the given target that is both
     * touchable and of the given type, if any.
     *
     * @param target    the locus of the search.
     * @param type      an {@link GameObject#getType() item type}.
     * @return          the closest touchable {@link GameObject} of the given type,
     *                  or {@code null}.
     */
    public static GameObject findClosest(MovableObject target, Identifier type) {
        return findClosest(target, target, touchableWithType(type));
    }

    public static GameObject findClosest(MovableObject movable, GameObject locus, Identifier type) {
        return findClosest(movable, locus, touchableWithType(type));
    }

    public static GameObject findClosest(MovableObject movable, Predicate<GameObject> applicability) {
        return findClosest(movable, movable, applicability);
    }

    public static GameObject findClosest(MovableObject source, GameObject locus, Predicate<GameObject> applicability) {
        GameObjectComposite group = source.getRoot();
        Collection<GameObject> gameObjects = group.findAll(applicability);
        return findClosest(source, locus, gameObjects);
    }

    public static GameObject findClosest(MovableObject source, GameObject locus, Collection<GameObject> gameObjects) {
        if (! gameObjects.isEmpty()) {
            List<GameObject> closest = new ArrayList<>(gameObjects);
            closest.sort(closestItem(locus));
            return findFirst(closest, hasPathTo(source));
        }
        return null;
    }

    /**
     * Returns a {@link Collection} containing all of the
     * {@link Player#isArtificial() artifical players} owned by the given
     * {@link GameObjectContainer}, if any.
     *
     * @param root  an {@code ItemRoot} instance.
     * @return      a {@code Collection} of {@link Player Players}
     *
     * @throws NullPointerException if the given {@code ItemRoot} is
     *                              {@code null}.
     */
    public static Collection<Player> getArtificialPlayers(GameObjectContainer root) {
        Objects.requireNonNull(root);
        Predicate<GameObject> query = both(UnitOperations::isPlayer, UnitOperations::isArtificial);
        Collection<GameObject> players = root.findAll(query);
        return CollectionUtils.convert(players, item -> (Player)item);
    }

    /**
     * Returns the {@link Player#isCorporeal() corporeal player} owned by the
     * given {@link GameObjectContainer}, if any.
     *
     * @param root  an {@code ItemRoot} instance.
     * @return      a {@link Player} instance.
     *
     * @throws NullPointerException if the given {@code ItemRoot} is
     *                              {@code null}.
     */
    public static Player getCorporealPlayer(GameObjectContainer root) {
        Objects.requireNonNull(root);
        Predicate<GameObject> query = both(UnitOperations::isPlayer, UnitOperations::isCorporeal);
        return (Player)root.find(query);
    }

    public static Collection<Player> getViewablePlayers(GameObjectContainer state) {
        Objects.requireNonNull(state);
        Predicate<GameObject> query = both(UnitOperations::isPlayer, UnitOperations::isViewable);
        Collection<GameObject> result = state.findAll(query);
        return CollectionUtils.convert(result, Player.class::cast);
    }

    /**
     * Returns the {@link Player} that the given {@link GameObject} belongs to, if
     * any (Terrain items, for example, aren't owned by a player).
     *
     * @param gameObject  an {@code Item} owned by a {@code Player}.
     * @return      the Player that owns the given Item, or {@code null} if
     *              the given Item isn't owned by a Player.
     *
     * @throws NullPointerException if the given {@code Item} is {@code null}.
     */
    public static Player getPlayer(GameObject gameObject) {
        if (gameObject instanceof Player) {
            return (Player) gameObject;
        }
        return (Player)findAncestor(gameObject, UnitOperations::isPlayer);
    }

    public static Collection<Player> getPlayers(GameObjectContainer state) {
        Collection<Player> players = new ArrayList<>();
        for (GameObject gameObject : state.getObjects()) {
            if (gameObject instanceof Player) {
                players.add((Player) gameObject);
            }
        }
        return players;
    }

    /**
     * Returns the {@link WarcraftFaction} that the given {@link GameObject} belongs
     * to.
     *
     * @param gameObject  an {@code Item} owned by a {@code Player}.
     * @return      the faction that the given Item belongs, or {@code null} if
     *              the given Item isn't owned by a Player.
     */
    public static WarcraftFaction getFaction(GameObject gameObject) {
        Player player = getPlayer(gameObject);
        if (player != null) {
            return player.getFaction();
        }
        return null;
    }

    public static boolean hasResources(GameObject gameObject, ResourceType type) {
        if (gameObject instanceof ResourceContainer) {
            ResourceContainer container = (ResourceContainer) gameObject;
            return container.getResource(type) > 0;
        }
        return false;
    }

    public static boolean hasUnit(Player player, UnitType type) {
        return GameObjectOperations.hasAny(player, hasType(type));
    }

    public static boolean hasUnits(Player player, UnitType ... types) {
        for (UnitType type: types) {
            if (! hasUnit(player, type)) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasUpgrade(Player player, Upgrade upgrade) {
        return player.hasUpgrade(upgrade);
    }

    /**
     * Determines if the given {@link GameObject Items} belong to different teams:
     * the both {@link Player#getTeam() players teams} are different.
     *
     * @param gameObjectA an {@code Item} to test.
     * @param gameObjectB another {@code Item} to test.
     * @return      {@code true} if both {@code Items} are owned by the same
     *              {@code Player}
     *
     * @throws NullPointerException thrown if either of the given {@code Items}
     *                              is {@code null}.
     */
    public static boolean isAnotherTeam(GameObject gameObjectA, GameObject gameObjectB) {
        Objects.requireNonNull(gameObjectA);
        Objects.requireNonNull(gameObjectB);

        Player playerA = getPlayer(gameObjectA);
        Player playerB = getPlayer(gameObjectB);

        return isAnotherTeam(playerA, playerB);
    }

    public static boolean isAnotherTeam(Player playerA, Player playerB) {
        int teamA = playerA != null ? playerA.getTeam() : -1;
        int teamB = playerB != null ? playerB.getTeam() : -1;
        return teamA != teamB;
    }

    /**
     * Determines if the given {@link GameObject} is "alive" of not. Specifically,
     * this method tests if the given {@code Item} implements {@link PerishableObject}
     * and if it has a {@link PerishableObject#getHealth() health} value more than
     * zero.
     *
     * @param gameObject a {@link PerishableObject} {@link GameObject}.
     *
     * @return  {@code true} if the given {@code Item} is a {@code Destroyable}
     *          and has a health value more than zero.
     */
    public static boolean isAlive(GameObject gameObject) {
        if (gameObject instanceof PerishableObject) {
            return isAlive((PerishableObject) gameObject);
        }
        return false;
    }

    private static boolean isAlive(PerishableObject object) {
        return object.getHealth() > 0;
    }

    public static boolean isDead(GameObject gameObject) {
        return !isAlive(gameObject);
    }

    /**
     * Determines if a given {@link GameObject} belongs to an artificial player,
     * inclusive of the neutral player.
     *
     * @param gameObject  an {@code Item} to test.
     * @return      {@code true} if the Item belongs to an AI player, otherwise
     *              {@code false}.
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
     *
     * @param gameObject  an {@code Item} to test.
     * @return      {@code true} if the Item belongs to an AI player, otherwise
     *              {@code false}.
     */
    public static boolean isArtificial(GameObject gameObject) {
        if (gameObject != null) {
            Player player = UnitOperations.getPlayer(gameObject);
            return player != null && player.isArtificial();
        }
        return false;
    }

    /**
     * Determines if a given {@link GameObject} belongs to the user operating the
     * current device.
     *
     * @param gameObject  an {@code Item} to test.
     * @return      {@code true} if the Item belongs to the user, otherwise
     *              {@code false}.
     */
    public static boolean isCorporeal(GameObject gameObject) {
        if (gameObject != null) {
            Player player = UnitOperations.getPlayer(gameObject);
            return player != null && player.isCorporeal();
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
            if (player != null) {
                return player.isCorporeal() || player.isControllable();
            }
        }
        return false;
    }

    /**
     * Determines if a given {@link GameObject} belongs to a
     * {@link Player#isViewable() viewable player}.
     */
    public static boolean isViewable(GameObject gameObject) {
        if (gameObject != null) {
            Player player = UnitOperations.getPlayer(gameObject);
            return player != null && player.isViewable();
        }
        return false;
    }

    /**
     * Determines if a given {@link GameObject} belongs to the neutral player, a
     * special AI player that owns resources and critters.
     *
     * @param gameObject  an {@code Item} to test.
     * @return      {@code true} if the Item belongs to the neutral player,
     *              otherwise {@code false}.
     */
    public static boolean isNeutral(GameObject gameObject) {
        if (gameObject != null) {
            Player player = UnitOperations.getPlayer(gameObject);
            return player != null && player.isNeutral();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a building.
     *
     * @param gameObject  an {@code Item} to test.
     * @return      {@code true} if the Item is a building, otherwise
     *              {@code false}.
     */
    public static boolean isBuilding(GameObject gameObject) {
        return gameObject instanceof Building;
    }

    public static boolean isSelector(GameObject gameObject) {
        return gameObject instanceof BuildingSelector || gameObject instanceof TargetSelector;
    }

    public static boolean isSelectorClear(GameObject gameObject) {
        if (gameObject instanceof BuildingSelector) {
            BuildingSelector selector = (BuildingSelector) gameObject;
            return selector.isClear();
        }
        return false;
    }

    public static boolean isConstructing(GameObject gameObject) {
        if (gameObject instanceof Building) {
            Building building = (Building) gameObject;
            return building.isConstructing();
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a combatant.
     *
     * @param gameObject  an {@code Item} to test.
     * @return      {@code true} if the Item is a combatant, otherwise
     *              {@code false}.
     */
    public static boolean isCombatant(GameObject gameObject) {
        return gameObject instanceof Combatant;
    }

    /**
     * Determines if the given {@link GameObject} can do damage to other Items.
     *
     * @param gameObject  an {@code Item} to test.
     * @return      {@code true} if the Item can do damage to others, otherwise
     *              {@code false}.
     */
    public static boolean isAttacker(GameObject gameObject) {
        if (gameObject instanceof OffensiveObject) {
            OffensiveObject combatant = (OffensiveObject) gameObject;
            return combatant.getAttackSpeed() > 0;
        }
        return false;
    }

    /**
     * Determines if the given {@link GameObject} is a critter.
     *
     * @param gameObject  an {@code Item} to test.
     * @return      {@code true} if the Item is a critter, otherwise
     *              {@code false}.
     */
    public static boolean isCritter(GameObject gameObject) {
        return gameObject instanceof Critter;
    }

    /**
     * Determines if the given {@link GameObject} produces food.
     *
     * @param gameObject  an {@code Item} to test.
     * @return      {@code true} if the Item produces food, otherwise
     *              {@code false}.
     */
    public static boolean isFoodProducer(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            Unit unit = (Unit) gameObject;
            UnitType type = (UnitType)unit.getType();
            return type.isFoodProducer();
        }
        return false;
    }

    public static boolean isTransport(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            Unit unit = (Unit) gameObject;
            UnitType type = (UnitType)unit.getType();
            return type.isTransport();
        }
        return false;
    }

    public static boolean isPlayer(GameObject gameObject) {
        return gameObject instanceof Player;
    }

    public static boolean isHighlighted(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            Unit unit = (Unit) gameObject;
            return unit.getHighlighted();
        }
        return false;
    }

    public static boolean isCastingSpell(GameObject gameObject, Spell spell) {
        if (gameObject instanceof SpellCaster) {
            SpellCaster spellCaster = (SpellCaster) gameObject;
            return spellCaster.getCastingSpell() == spell;
        }
        return false;
    }

    public static boolean inSight(OffensiveObject combatant, GameObject target) {
        return isNear(combatant, combatant.getSight(), target);
    }

    public static boolean inRange(OffensiveObject combatant, GameObject target) {
        return isNear(combatant, combatant.getAttackRange(), target);
    }

    public static boolean isRanged(GameObject gameObject) {
        return gameObject instanceof RangedCombatant;
    }

    public static boolean isResource(GameObject gameObject) {
        return gameObject instanceof Resource;
    }

    public static boolean isShip(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            Unit unit = (Unit) gameObject;
            UnitType type = (UnitType)unit.getType();
            return type.isNavalUnit();
        }
        return false;
    }

    public static boolean isSubmarine(GameObject gameObject) {
        return gameObject instanceof Submarine;
    }

    public static boolean isFlying(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            Unit unit = (Unit) gameObject;
            UnitType type = (UnitType)unit.getType();
            return type.isFlying();
        }
        return false;
    }

    public static void reorient(MovableObject subject, GameObject target, boolean perpendicular) {
        Vector2 itemPosition = subject.getPosition();
        Vector2 targetPosition = target.getPosition();
        Vector2 direction = targetPosition.sub(itemPosition);
        Vector2 normalizedDirection = direction.nor();
        Vector2 newDirection = normalizedDirection.rotate(perpendicular ? 90 : 0);
        subject.setDirection(newDirection);
    }

    public static void moveAdjacent(MovableObject subject, GameObject target) {
        GameObjectContainer root = target.getRoot();
        GameObjectGraph graph = root.getSpatialGraph();

        ItemPathFilter capability = new ItemPathFilter();
        capability.addTraversableCapability(subject.getMovementCapability());

        Collection<GameObjectNode> adjacent = graph.getAdjacentNodes(target.getPosition(), target.getSize());
        Optional<GameObjectNode> unoccupied = adjacent.stream().filter(capability).findFirst();

        if (unoccupied.isPresent()) {
            graph.removeOccupants(subject);
            GameObjectNode destination = unoccupied.get();
            subject.setPosition(destination.getWorldReference());
            graph.addOccupants(subject);
        }
    }
}
