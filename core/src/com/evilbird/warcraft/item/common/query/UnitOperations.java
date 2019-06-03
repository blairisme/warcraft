/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.query;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.common.movement.Movable;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

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
import static com.evilbird.warcraft.item.common.query.UnitPredicates.hasPathTo;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAi;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isPlayer;

/**
 * Instances of this class contain common operations for working with Items.
 *
 * @author Blair Butterworth
 */
public class UnitOperations
{
    private UnitOperations() {
    }

    public static Item findClosest(Movable source, Identifier type) {
        return findClosest(source, type, source);
    }

    public static Item findClosest(Movable source, Identifier type, Item locus) {
        ItemComposite group = source.getRoot();
        Collection<Item> items = group.findAll(touchableWithType(type));
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

    public static Player getPlayer(Item item) {
        return (Player)findAncestor(item, withClazz(Player.class));
    }

    public static Player getHumanPlayer(Item worldItem) {
        ItemRoot itemRoot = worldItem.getRoot();
        return getHumanPlayer(itemRoot);
    }

    public static Player getHumanPlayer(ItemRoot itemRoot) {
        Predicate<Item> query = both(isPlayer(), UnitPredicates.isCorporeal());
        return (Player)itemRoot.find(query);
    }

    public static boolean isHuman(Item item) {
        if (! (item instanceof Player)) {
            item = item.getParent();
        }
        if (item instanceof Player) {
            Player player = (Player) item;
            return player.isCorporeal();
        }
        return false;
    }

    public static Player getAiPlayer(ItemRoot itemRoot) {
        Predicate<Item> query = both(isPlayer(), isAi());
        return (Player)itemRoot.find(query);
    }

    public static boolean inSight(Combatant combatant, Item target) {
        return isNear(combatant, combatant.getSight(), target);
    }

    public static boolean inRange(Combatant combatant, Item target) {
        return isNear(combatant, combatant.getRange(), target);
    }
}
