/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.query;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.engine.item.utility.ItemOperations;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.item.common.movement.Movable;
import com.evilbird.warcraft.item.common.movement.MovementCapability;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.placement.Placeholder;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.item.unit.critter.Critter;
import com.evilbird.warcraft.item.unit.resource.Resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import static com.evilbird.engine.common.collection.CollectionUtils.findFirst;
import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.utility.ItemComparators.closestItem;
import static com.evilbird.engine.item.utility.ItemOperations.findAncestor;
import static com.evilbird.engine.item.utility.ItemOperations.isNear;
import static com.evilbird.engine.item.utility.ItemPredicates.touchableWithType;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.hasPathTo;

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
     * Returns the {@link Item} closest to the given target that is both
     * touchable and of the given type, if any.
     *
     * @param target    the locus of the search.
     * @param type      an {@link Item#getType() item type}.
     * @return          the closest touchable {@link Item} of the given type,
     *                  or {@code null}.
     */
    public static Item findClosest(Movable target, Identifier type) {
        return findClosest(target, target, touchableWithType(type));
    }

    public static Item findClosest(Movable movable, Item locus, Identifier type) {
        return findClosest(movable, locus, touchableWithType(type));
    }

    public static Item findClosest(Movable movable, Predicate<Item> applicability) {
        return findClosest(movable, movable, applicability);
    }

    public static Item findClosest(Movable source, Item locus, Predicate<Item> applicability) {
        ItemComposite group = source.getRoot();
        Collection<Item> items = group.findAll(applicability);
        return findClosest(source, locus, items);
    }

    public static Item findClosest(Movable source, Item locus, Collection<Item> items) {
        if (! items.isEmpty()) {
            List<Item> closest = new ArrayList<>(items);
            closest.sort(closestItem(locus));
            return findFirst(closest, hasPathTo(source));
        }
        return null;
    }

    /**
     * Returns a {@link Collection} containing all of the
     * {@link Player#isArtificial() artifical players} owned by the given
     * {@link ItemRoot}, if any.
     *
     * @param root  an {@code ItemRoot} instance.
     * @return      a {@code Collection} of {@link Player Players}
     *
     * @throws NullPointerException if the given {@code ItemRoot} is
     *                              {@code null}.
     */
    public static Collection<Player> getArtificialPlayers(ItemRoot root) {
        Objects.requireNonNull(root);
        Predicate<Item> query = both(UnitOperations::isPlayer, UnitOperations::isArtificial);
        Collection<Item> players = root.findAll(query);
        return CollectionUtils.convert(players, item -> (Player)item);
    }

    /**
     * Returns the {@link Player#isCorporeal() corporeal player} owned by the
     * given {@link ItemRoot}, if any.
     *
     * @param root  an {@code ItemRoot} instance.
     * @return      a {@link Player} instance.
     *
     * @throws NullPointerException if the given {@code ItemRoot} is
     *                              {@code null}.
     */
    public static Player getCorporealPlayer(ItemRoot root) {
        Objects.requireNonNull(root);
        Predicate<Item> query = both(UnitOperations::isPlayer, UnitOperations::isCorporeal);
        return (Player)root.find(query);
    }

    /**
     * Returns the {@link Player} that the given {@link Item} belongs to, if
     * any (Terrain items, for example, aren't owned by a player).
     *
     * @param item  an {@code Item} owned by a {@code Player}.
     * @return      the Player that owns the given Item, or {@code null} if
     *              the given Item isn't owned by a Player.
     *
     * @throws NullPointerException if the given {@code Item} is {@code null}.
     */
    public static Player getPlayer(Item item) {
        if (item instanceof Player) {
            return (Player)item;
        }
        return (Player)findAncestor(item, UnitOperations::isPlayer);
    }

    /**
     * Returns the {@link WarcraftFaction} that the given {@link Item} belongs
     * to.
     *
     * @param item  an {@code Item} owned by a {@code Player}.
     * @return      the faction that the given Item belongs, or {@code null} if
     *              the given Item isn't owned by a Player.
     */
    public static WarcraftFaction getFaction(Item item) {
        Player player = getPlayer(item);
        if (player != null) {
            return player.getFaction();
        }
        return null;
    }

    public static boolean hasResources(Item item, ResourceType type) {
        if (item instanceof ResourceContainer) {
            ResourceContainer container = (ResourceContainer)item;
            return container.getResource(type) > 0;
        }
        return false;
    }

    public static boolean hasUnit(Player player, UnitType type) {
        return ItemOperations.hasAny(player, withType(type));
    }

    public static boolean hasUpgrade(Player player, Upgrade upgrade) {
        return player.hasUpgrade(upgrade);
    }

    /**
     * Determines if the given {@link Item Items} belong to different teams:
     * the {@link Player players} that own them are different.
     *
     * @param itemA an {@code Item} to test.
     * @param itemB another {@code Item} to test.
     * @return      {@code true} if both {@code Items} are owned by the same
     *              {@code Player}
     *
     * @throws NullPointerException thrown if either of the given {@code Items}
     *                              is {@code null}.
     */
    public static boolean isAnotherTeam(Item itemA, Item itemB) {
        Objects.requireNonNull(itemA);
        Objects.requireNonNull(itemB);
        Player playerA = getPlayer(itemA);
        Player playerB = getPlayer(itemB);
        return playerA != null && playerB != null && playerA != playerB;
    }

    /**
     * Determines if the given {@link Item} is "alive" of not. Specifically,
     * this method tests if the given {@code Item} implements {@link Destroyable}
     * and if it has a {@link Destroyable#getHealth() health} value more than
     * zero.
     *
     * @param item a {@link Destroyable} {@link Item}.
     *
     * @return  {@code true} if the given {@code Item} is a {@code Destroyable}
     *          and has a health value more than zero.
     */
    public static boolean isAlive(Item item) {
        if (item instanceof Destroyable) {
            Destroyable destroyable = (Destroyable)item;
            return destroyable.getHealth() > 0;
        }
        return false;
    }

    public static boolean isDead(Item item) {
        return !isAlive(item);
    }

    /**
     * Determines if a given {@link Item} belongs to an artificial player,
     * inclusive of the neutral player.
     *
     * @param item  an {@code Item} to test.
     * @return      {@code true} if the Item belongs to an AI player, otherwise
     *              {@code false}.
     */
    public static boolean isAi(Item item) {
        if (item != null) {
            Player player = UnitOperations.getPlayer(item);
            return player != null && !player.isCorporeal();
        }
        return false;
    }

    /**
     * Determines if a given {@link Item} belongs to an artificial player,
     * excluding the neutral player.
     *
     * @param item  an {@code Item} to test.
     * @return      {@code true} if the Item belongs to an AI player, otherwise
     *              {@code false}.
     */
    public static boolean isArtificial(Item item) {
        if (item != null) {
            Player player = UnitOperations.getPlayer(item);
            return player != null && player.isArtificial();
        }
        return false;
    }

    /**
     * Determines if a given {@link Item} belongs to the user operating the
     * current device.
     *
     * @param item  an {@code Item} to test.
     * @return      {@code true} if the Item belongs to the user, otherwise
     *              {@code false}.
     */
    public static boolean isCorporeal(Item item) {
        if (item != null) {
            Player player = UnitOperations.getPlayer(item);
            return player != null && player.isCorporeal();
        }
        return false;
    }

    /**
     * Determines if a given {@link Item} belongs to the neutral player, a
     * special AI player that owns resources and critters.
     *
     * @param item  an {@code Item} to test.
     * @return      {@code true} if the Item belongs to the neutral player,
     *              otherwise {@code false}.
     */
    public static boolean isNeutral(Item item) {
        if (item != null) {
            Player player = UnitOperations.getPlayer(item);
            return player != null && player.isNeutral();
        }
        return false;
    }

    /**
     * Determines if the given {@link Item} is a building.
     *
     * @param item  an {@code Item} to test.
     * @return      {@code true} if the Item is a building, otherwise
     *              {@code false}.
     */
    public static boolean isBuilding(Item item) {
        return item instanceof Building;
    }

    public static boolean isBuildingPlaceholder(Item item) {
        return item instanceof Placeholder;
    }

    public static boolean isConstructing(Item item) {
        if (item instanceof Building) {
            Building building = (Building) item;
            return building.isConstructing();
        }
        return false;
    }

    /**
     * Determines if the given {@link Item} is a combatant.
     *
     * @param item  an {@code Item} to test.
     * @return      {@code true} if the Item is a combatant, otherwise
     *              {@code false}.
     */
    public static boolean isCombatant(Item item) {
        return item instanceof Combatant;
    }

    /**
     * Determines if the given {@link Item} can do damage to other Items.
     *
     * @param item  an {@code Item} to test.
     * @return      {@code true} if the Item can do damage to others, otherwise
     *              {@code false}.
     */
    public static boolean isAttacker(Item item) {
        if (item instanceof Combatant) {
            Combatant combatant = (Combatant)item;
            return combatant.getAttackSpeed() > 0;
        }
        return false;
    }



    /**
     * Determines if the given {@link Item} is a critter.
     *
     * @param item  an {@code Item} to test.
     * @return      {@code true} if the Item is a critter, otherwise
     *              {@code false}.
     */
    public static boolean isCritter(Item item) {
        return item instanceof Critter;
    }

    /**
     * Determines if the given {@link Item} produces food.
     *
     * @param item  an {@code Item} to test.
     * @return      {@code true} if the Item produces food, otherwise
     *              {@code false}.
     */
    public static boolean isFoodProducer(Item item) {
        if (item instanceof Unit) {
            Unit unit = (Unit)item;
            UnitType type = (UnitType)unit.getType();
            return type.isFoodProducer();
        }
        return false;
    }

    public static boolean isTransport(Item item) {
        if (item instanceof Unit) {
            Unit unit = (Unit)item;
            UnitType type = (UnitType)unit.getType();
            return type.isTransport();
        }
        return false;
    }

    /**
     * Determines if the given {@link Item} belongs to the human faction/race.
     *
     * @param item  an {@code Item} to test.
     * @return      {@code true} if the Item is a human, otherwise
     *              {@code false}.
     */
    public static boolean isHuman(Item item) {
        Player player = getPlayer(item);
        return player != null && player.getFaction() == Human;
    }

    /**
     * Determines if the given {@link Item} belongs to the orc faction/race.
     *
     * @param item  an {@code Item} to test.
     * @return      {@code true} if the Item is an orc, otherwise
     *              {@code false}.
     */
    public static boolean isOrc(Item item) {
        Player player = getPlayer(item);
        return player != null && player.getFaction() == Orc;
    }

    public static boolean isPlayer(Item item) {
        return item instanceof Player;
    }

    public static boolean inSight(Combatant combatant, Item target) {
        return isNear(combatant, combatant.getSight(), target);
    }

    public static boolean inRange(Combatant combatant, Item target) {
        return isNear(combatant, combatant.getAttackRange(), target);
    }

    public static boolean isRanged(Item item) {
        return item instanceof RangedCombatant;
    }

    public static boolean isResource(Item item) {
        return item instanceof Resource;
    }

    public static boolean isShip(Combatant combatant) {
        return combatant.getMovementCapability() == MovementCapability.Water;
    }

    public static boolean isUnit(Item item) {
        return item instanceof Unit;
    }

    public static void reorient(Unit item, Item target, boolean perpendicular) {
        Vector2 itemPosition = item.getPosition();
        Vector2 targetPosition = target.getPosition();
        Vector2 direction = targetPosition.sub(itemPosition);
        Vector2 normalizedDirection = direction.nor();
        Vector2 newDirection = normalizedDirection.rotate(perpendicular ? 90 : 0);
        item.setDirection(newDirection);
    }

    public static void moveAdjacent(Movable subject, Item target) {
        ItemRoot root = target.getRoot();
        ItemGraph graph = root.getSpatialGraph();

        ItemPathFilter capability = new ItemPathFilter();
        capability.addTraversableCapability(subject.getMovementCapability());

        Collection<ItemNode> adjacent = graph.getAdjacentNodes(target.getPosition(), target.getSize());
        Optional<ItemNode> unoccupied = adjacent.stream().filter(capability).findFirst();

        if (unoccupied.isPresent()) {
            graph.removeOccupants(subject);
            ItemNode destination = unoccupied.get();
            subject.setPosition(destination.getWorldReference());
            graph.addOccupants(subject);
        }
    }
}
