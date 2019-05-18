/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.query;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.LambdaAction;
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.common.lang.Movable;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.placeholder.Placeholder;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import java.util.Objects;
import java.util.function.Predicate;

import static com.evilbird.engine.item.utility.ItemOperations.isNear;
import static com.evilbird.warcraft.action.common.path.ItemPathFinder.hasPath;

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
            if (! (item instanceof Player)) {
                item = item.getParent();
            }
            if (item instanceof Player) {
                Player player = (Player) item;
                return !player.isCorporeal();
            }
            return false;
        };
    }

    public static Predicate<Item> isHuman() {
        return (item) -> {
            if (! (item instanceof Player)) {
                item = item.getParent();
            }
            if (item instanceof Player) {
                Player player = (Player) item;
                return player.isCorporeal();
            }
            return false;
        };
    }

    public static Predicate<Item> isBuilding() {
        return (item) -> item instanceof Building;
    }

    public static Predicate<Item> isCombatant() {
        return (item) -> item instanceof Combatant;
    }

    public static Predicate<Item> isPlaceholder() {
        return (item) -> item instanceof Placeholder;
    }

    public static Predicate<Item> isPlayer() {
        return (item) -> Objects.equals(item.getType(), DataType.Player);
    }

    public static Predicate<Item> isSelected() {
        return (item) -> {
            if (item instanceof Unit) {
                Unit unit = (Unit)item;
                return unit.getSelected();
            }
            return false;

        };
    }

    public static Predicate<Item> hasResources(ResourceType type) {
        return (item) -> {
            ResourceContainer container = (ResourceContainer)item;
            return container.getResource(type) > 0;
        };
    }

    public static Predicate<Item> hasPathTo(Movable source) {
        return destination -> hasPath(source, destination);
    }

    public static Predicate<Item> noResources(ResourceType type) {
        return (item) -> {
            ResourceContainer container = (ResourceContainer)item;
            return container.getResource(type) == 0;
        };
    }

    public static Predicate<Item> isAdjacent(Item locus) {
        Objects.requireNonNull(locus);
        return item -> item != null && isNear(locus, locus.getWidth(), item);
    }

    public static Predicate<Item> notAdjacent(Item item) {
        return Predicates.not(isAdjacent(item));
    }

    public static Predicate<Item> inRange(Combatant combatant) {
        Objects.requireNonNull(combatant);
        return item -> item != null && isNear(combatant, combatant.getRange(), item);
    }

    public static Predicate<Item> notInRange(Combatant combatant) {
        return Predicates.not(inRange(combatant));
    }

    public static Predicate<Item> isUnderConstruction() {
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

    public static Predicate<Item> isGathererConstructing() {
        return (item) -> {
            if (item instanceof Gatherer) {
                Gatherer gatherer = (Gatherer)item;
                return gatherer.isConstructing();
            }
            return false;
        };
    }

    public static Predicate<Item> isPlaceholderClear() {
        return item -> {
            if (item instanceof Placeholder) {
                Placeholder placeholder = (Placeholder)item;
                return placeholder.isClear();
            }
            return false;
        };
    }

    public static Action assignConstruction() {
        return new LambdaAction((subject, target) -> {
            if (subject instanceof Gatherer) {
                Gatherer gatherer = (Gatherer)subject;
                gatherer.setConstruction(target);
            }
        });
    }

    public static Action unassignConstruction() {
        return new LambdaAction((subject, target) -> {
            if (subject instanceof Gatherer) {
                Gatherer gatherer = (Gatherer)subject;
                gatherer.setConstruction(null);
            }
        });
    }
}
