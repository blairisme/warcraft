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
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.Objects;

/**
 * Defines commonly used {@link Predicate Predicates} that operate on
 * {@link Unit Units}.
 *
 * @author Blair Butterworth
 */
public class UnitPredicates
{
    private UnitPredicates() {
        throw new UnsupportedOperationException();
    }

    public static Predicate<Unit> isAlive() {
        return Unit::isAlive;
    }

    public static Predicate<Item> isAi() {
        return (item) -> {
            Player player = (Player)item.getParent();
            return !player.isHumanPlayer();
        };
    }

    public static Predicate<Item> isHuman() {
        return (item) -> {
            Player player = (Player)item;
            return player.isHumanPlayer();
        };
    }

    public static Predicate<Item> isPlayer() {
        return (item) -> Objects.equals(item.getType(), DataType.Player);
    }
}
