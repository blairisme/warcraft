/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.query;

import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.data.player.Player;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.ItemOperations.findAncestor;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isHuman;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isPlayer;

/**
 * @author Blair Butterworth
 */
public class UnitOperations
{
    private UnitOperations() {
    }

    public static Player getHumanPlayer(Item worldItem) {
        ItemRoot itemRoot = worldItem.getRoot();
        return getHumanPlayer(itemRoot);
    }

    public static Player getHumanPlayer(ItemRoot itemRoot) {
        Predicate<Item> query = both(isPlayer(), isHuman());
        return (Player)itemRoot.find(query);
    }

    public static Player getAncestorPlayer(Item worldItem) {
        Predicate<Item> query = isPlayer();
        return (Player)findAncestor(worldItem, query);
    }
}
