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
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.movement.Movable;
import com.evilbird.warcraft.item.common.movement.MovementCapability;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.placement.Placeholder;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import com.evilbird.warcraft.item.unit.resource.Resource;

import java.util.Objects;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.not;
import static com.evilbird.engine.item.utility.ItemOperations.isNear;
import static com.evilbird.engine.item.utility.ItemPredicates.hasType;
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
            if (item instanceof Destroyable) {
                Destroyable destroyable = (Destroyable)item;
                return destroyable.getHealth() > 0;
            }
            return false;
        };
    }

    public static Predicate<Item> isAi() {
        return (item) -> {
            if (item == null) {
                return false;
            }
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

    public static Predicate<Item> isCorporeal() {
        return (item) -> {
            if (item == null) {
                return false;
            }
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

    public static Predicate<Item> notCombatant() {
        return not(isCombatant());
    }

    public static Predicate<Item> isDepotFor(ResourceType resource) {
        return item -> {
            Identifier type = item.getType();
            if (type == UnitType.TownHall || type == UnitType.GreatHall) {
                return true;
            }
            if (resource == ResourceType.Gold && type == UnitType.LumberMill) {
                return true;
            }
            return false;
        };
    }

    public static Predicate<Item> isDestroyable() {
        return item -> item instanceof Destroyable;
    }

    public static Predicate<Item> isGatherer() {
        return (item) -> item instanceof Gatherer;
    }

    public static Predicate<Item> isMovable() {
        return item -> item instanceof Movable;
    }

    public static Predicate<Item> isMovableOver(MovementCapability capability) {
        return item -> {
            if (item instanceof Movable) {
                Movable movable = (Movable)item;
                return capability.equals(movable.getMovementCapability());
            }
            return false;
        };
    }

    public static Predicate<Item> isPlaceholder() {
        return (item) -> item instanceof Placeholder;
    }

    public static Predicate<Item> isPlayer() {
        return (item) -> item instanceof Player;
    }

    public static Predicate<Item> isResource() {
        return item -> item instanceof Resource;
    }

    public static Predicate<Item> isSelected() {
        return (item) -> {
            if (item instanceof Selectable) {
                Selectable selectable = (Selectable)item;
                return selectable.getSelected();
            }
            return false;
        };
    }

    public static Predicate<Item> isSelectable() {
        return (item) -> item instanceof Selectable;
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

    public static Predicate<Item> inRange(Combatant combatant) {
        Objects.requireNonNull(combatant);
        return item -> item != null && isNear(combatant, combatant.getRange(), item);
    }

    public static Predicate<Item> notInRange(Combatant combatant) {
        return not(inRange(combatant));
    }

    public static Predicate<Item> isRanged() {
        return item -> UnitOperations.isRanged(item);
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

    public static Predicate<Item> isPlaceholderClear() {
        return item -> {
            if (item instanceof Placeholder) {
                Placeholder placeholder = (Placeholder)item;
                return placeholder.isClear();
            }
            return false;
        };
    }

    public static Predicate<Item> associatedWith(Identifier type) {
        return associatedWith(hasType(type));
    }

    public static Predicate<Item> associatedWith(Predicate<Item> condition) {
        return (item) -> {
            if (item instanceof Unit) {
                Unit unit = (Unit)item;
                Item associated = unit.getAssociatedItem();
                return condition.test(associated);
            }
            return false;
        };
    }
}
