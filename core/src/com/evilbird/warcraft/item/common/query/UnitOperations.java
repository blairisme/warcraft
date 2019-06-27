/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.query;

import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.utility.ItemOperations;
import com.evilbird.warcraft.item.common.movement.Movable;
import com.evilbird.warcraft.item.common.movement.MovementCapability;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
import com.evilbird.warcraft.item.unit.UnitCosts;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import static com.evilbird.engine.common.collection.CollectionUtils.findFirst;
import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.utility.ItemComparators.closestItem;
import static com.evilbird.engine.item.utility.ItemOperations.findAncestor;
import static com.evilbird.engine.item.utility.ItemOperations.isNear;
import static com.evilbird.engine.item.utility.ItemPredicates.touchableWithType;
import static com.evilbird.engine.item.utility.ItemPredicates.withClazz;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.hasPathTo;

/**
 * Instances of this class contain common operations for working with Items.
 *
 * @author Blair Butterworth
 */
public class UnitOperations
{
    private UnitOperations() {
    }

    public static Item findClosest(Movable movable, Identifier type) {
        return findClosest(movable, movable, touchableWithType(type));
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

    public static Player getAiPlayer(ItemRoot itemRoot) {
        Predicate<Item> query = both(UnitPredicates.isPlayer(), UnitPredicates.isAi());
        return (Player)itemRoot.find(query);
    }

    public static Player getPlayer(Item item) {
        return (Player)findAncestor(item, withClazz(Player.class));
    }

    public static Player getCorporealPlayer(Item worldItem) {
        ItemRoot itemRoot = worldItem.getRoot();
        return getCorporealPlayer(itemRoot);
    }

    public static Player getCorporealPlayer(ItemRoot itemRoot) {
        Predicate<Item> query = both(UnitPredicates.isPlayer(), UnitPredicates.isCorporeal());
        return (Player)itemRoot.find(query);
    }

    public static boolean hasResources(Player player, UnitType type) {
        for (ResourceQuantity cost: UnitCosts.cost(type)) {
            if (player.getResource(cost.getResource()) < cost.getValue()){
                return false;
            }
        }
        return true;
    }

    public static boolean hasResources(Player player, PlayerUpgrade upgrade) {
        for (ResourceQuantity cost: UnitCosts.cost(upgrade)) {
            if (player.getResource(cost.getResource()) < cost.getValue()){
                return false;
            }
        }
        return true;
    }

    public static boolean hasUnit(Player player, UnitType type) {
        return ItemOperations.hasAny(player, withType(type));
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

    public static boolean isAi(Item item) {
        if (item != null) {
            if (!(item instanceof Player)) {
                item = item.getParent();
            }
            if (item instanceof Player) {
                Player player = (Player)item;
                return !player.isCorporeal();
            }
        }
        return false;
    }

    public static boolean isCorporeal(Item item) {
        if (item != null) {
            if (!(item instanceof Player)) {
                item = item.getParent();
            }
            if (item instanceof Player) {
                Player player = (Player)item;
                return player.isCorporeal();
            }
        }
        return false;
    }

    public static boolean inSight(Combatant combatant, Item target) {
        return isNear(combatant, combatant.getSight(), target);
    }

    public static boolean inRange(Combatant combatant, Item target) {
        return isNear(combatant, combatant.getRange(), target);
    }

    public static boolean isRanged(Item item) {
        return item instanceof RangedCombatant;
    }

    public static boolean isShip(Combatant combatant) {
        return combatant.getMovementCapability() == MovementCapability.Water;
    }
}
