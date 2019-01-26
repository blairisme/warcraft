/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.data.player.Player;

/**
 * Instances of this class provide commonly used {@link Predicate Predicates}
 * that operate on {@link Unit Units}.
 *
 * @author Blair Butterworth
 */
public class UnitPredicates
{
    private UnitPredicates() {
        throw new UnsupportedOperationException();
    }

    public static Predicate<Unit> isAlive() {
        return new Predicate<Unit>() {
            @Override
            public boolean test(Unit unit) {
                return unit.isAlive();
            }
        };
    }

    public static Predicate<Item> isAi() {
        return new Predicate<Item>() {
            @Override
            public boolean test(Item item) {
                Player player = (Player)item.getParent();
                return !player.isHumanPlayer();
            }
        };
    }
}
