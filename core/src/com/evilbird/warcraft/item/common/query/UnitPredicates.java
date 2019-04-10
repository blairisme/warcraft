/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.query;

import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.utility.ItemPredicates;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import java.util.Objects;
import java.util.function.Predicate;

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

    public static Predicate<Item> isAlive() {
        return (item) -> {
            Unit unit = (Unit)item;
            return unit.isAlive();
        };
    }

    public static Predicate<Item> isDead() {
        return (item) -> {
            Unit unit = (Unit)item;
            return !unit.isAlive();
        };
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

    public static Predicate<Item> isSelected() {
        return Item::getSelected;
    }

    public static Predicate<Item> isSelected(Item item) {
        final boolean selected = item.getSelected();
        return (it) -> selected;
    }

    public static Predicate<Item> isUnselected() {
        return (item) -> !item.getSelected();
    }

    public static Predicate<Item> isUnselected(Item item) {
        final boolean selected = item.getSelected();
        return (it) -> !selected;
    }

    public static Predicate<Item> hasResources(ResourceType type) {
        return (item) -> {
            ResourceContainer container = (ResourceContainer)item;
            return container.getResource(type) > 0;
        };
    }

    public static Predicate<Item> noResources(ResourceType type) {
        return (item) -> {
            ResourceContainer container = (ResourceContainer)item;
            return container.getResource(type) == 0;
        };
    }

    public static Predicate<Item> isAdjacent(Item item) {
        return ItemPredicates.isNear(item, item.getWidth());
    }

    public static Predicate<Item> notAdjacent(Item item) {
        return Predicates.not(isAdjacent(item));
    }

    public static Predicate<Item> inRange(Combatant combatant) {
        return ItemPredicates.isNear(combatant, combatant.getRange());
    }

    public static Predicate<Item> notInRange(Combatant combatant) {
        return Predicates.not(inRange(combatant));
    }

    public static Predicate<Item> isConstructing() {
        return (item) -> {
            Building building = (Building)item;
            return building.isConstructing();
        };
    }

    public static Predicate<Item> isProducing() {
        return (item) -> {
            Building building = (Building)item;
            return building.isProducing();
        };
    }
}
