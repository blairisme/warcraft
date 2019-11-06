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
import com.evilbird.warcraft.item.common.capability.MovableObject;
import com.evilbird.warcraft.item.common.capability.MovementCapability;
import com.evilbird.warcraft.item.common.capability.PerishableObject;
import com.evilbird.warcraft.item.common.capability.SelectableObject;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.common.spell.Spell;
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
import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Oil;
import static com.evilbird.warcraft.item.common.resource.ResourceType.Wood;

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

    /**
     * Returns a condition that determines if the given {@link Item} is "alive"
     * of not. Specifically, this method tests if the given {@code Item}
     * implements {@link PerishableObject} and if it has a
     * {@link PerishableObject#getHealth() health} value more than zero.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<Item> isAlive() {
        return UnitOperations::isAlive;
    }

    public static Predicate<Item> isDead() {
        return UnitOperations::isDead;
    }

    /**
     * Returns a condition that determines if a given {@link Item} belongs to
     * an artificial player, inclusive of the neutral player.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<Item> isAi() {
        return UnitOperations::isAi;
    }

    /**
     * Returns a condition that determines if a given {@link Item} belongs to
     * the user operating the current device.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<Item> isCorporeal() {
        return UnitOperations::isCorporeal;
    }

    /**
     * Returns a condition that determines if a given {@link Item} belongs to
     * the corporeal player, the user, or to a {@link Player#isControllable()
     * controllable player}.
     */
    public static Predicate<Item> isControllable() {
        return UnitOperations::isControllable;
    }

    /**
     * Returns a condition that determines if a given {@link Item} belongs to
     * the neutral user, a special AI player that owns resources and critters.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<Item> isNeutral() {
        return UnitOperations::isNeutral;
    }

    /**
     * Returns a condition that determines if the given {@link Item} is a
     * building.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<Item> isBuilding() {
        return UnitOperations::isBuilding;
    }

    /**
     * Returns a condition that determines if the given {@link Item} is a
     * combatant.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<Item> isCombatant() {
        return UnitOperations::isCombatant;
    }

    public static Predicate<Item> isNonCombatant() {
        return not(isCombatant());
    }

    public static Predicate<Item> isAttacker() {
        return UnitOperations::isAttacker;
    }

    public static Predicate<Item> isTransport() {
        return UnitOperations::isTransport;
    }

    /**
     * Returns a condition that determines if the given {@link Item} is a
     * critter.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<Item> isCritter() {
        return UnitOperations::isCritter;
    }

    public static Predicate<Item> isDepotFor(ResourceType resource) {
        return item -> {
            if (item instanceof Building) {
                UnitType type = (UnitType) item.getType();
                return (resource == Gold && type.isGoldDepot())
                    || (resource == Oil && type.isOilDepot())
                    || (resource == Wood && type.isWoodDepot());
            }
            return false;
        };
    }

    public static Predicate<Item> isFoodProducer() {
        return UnitOperations::isFoodProducer;
    }

    public static Predicate<Item> isDestroyable() {
        return item -> item instanceof PerishableObject;
    }

    public static Predicate<Item> isGatherer() {
        return (item) -> item instanceof Gatherer;
    }

    public static Predicate<Item> isMovable() {
        return item -> item instanceof MovableObject;
    }

    public static Predicate<Item> isMovableOver(MovementCapability capability) {
        return item -> {
            if (item instanceof MovableObject) {
                MovableObject movable = (MovableObject)item;
                return capability.equals(movable.getMovementCapability());
            }
            return false;
        };
    }

    public static Predicate<Item> isCastingSpell(Spell spell) {
        return item -> UnitOperations.isCastingSpell(item, spell);
    }

    public static Predicate<Item> isPlaceholder() {
        return (item) -> item instanceof Placeholder;
    }

    public static Predicate<Item> isPlayer() {
        return UnitOperations::isPlayer;
    }

    public static Predicate<Item> isResource() {
        return item -> item instanceof Resource;
    }

    public static Predicate<Item> isSelected() {
        return (item) -> {
            if (item instanceof SelectableObject) {
                SelectableObject selectable = (SelectableObject)item;
                return selectable.getSelected();
            }
            return false;
        };
    }

    public static Predicate<Item> isSelectable() {
        return (item) -> item instanceof SelectableObject;
    }

    public static Predicate<Item> hasResources(ResourceType type) {
        return (item) -> {
            ResourceContainer container = (ResourceContainer)item;
            return container.getResource(type) > 0;
        };
    }

    public static Predicate<Item> hasPathTo(MovableObject source) {
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
        return item -> item != null && isNear(combatant, combatant.getAttackRange(), item);
    }

    public static Predicate<Item> notInRange(Combatant combatant) {
        return not(inRange(combatant));
    }

    public static Predicate<Item> isRanged() {
        return UnitOperations::isRanged;
    }

    public static Predicate<Item> isConstructing() {
        return UnitOperations::isConstructing;
    }

    public static Predicate<Item> isGathering() {
        return (item) -> {
            Gatherer gatherer = (Gatherer)item;
            return gatherer.isGathering();
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

    public static Predicate<Item> associatedWith(Item withItem) {
        return (item) -> item instanceof Unit && ((Unit)item).getAssociatedItem() == withItem;
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
