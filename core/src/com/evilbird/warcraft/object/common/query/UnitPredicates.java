/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.query;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.SelectableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.common.resource.ResourceContainer;
import com.evilbird.warcraft.object.common.resource.ResourceType;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.gatherer.Gatherer;
import com.evilbird.warcraft.object.unit.resource.Resource;

import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.not;
import static com.evilbird.engine.object.utility.GameObjectPredicates.hasType;
import static com.evilbird.warcraft.action.common.path.ItemPathFinder.hasPath;
import static com.evilbird.warcraft.object.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.object.common.resource.ResourceType.Oil;
import static com.evilbird.warcraft.object.common.resource.ResourceType.Wood;

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
     * Returns a condition that determines if the given {@link GameObject} is "alive"
     * of not. Specifically, this method tests if the given {@code Item}
     * implements {@link PerishableObject} and if it has a
     * {@link PerishableObject#getHealth() health} value more than zero.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<GameObject> isAlive() {
        return UnitOperations::isAlive;
    }

    public static Predicate<GameObject> isDead() {
        return UnitOperations::isDead;
    }

    /**
     * Returns a condition that determines if a given {@link GameObject} belongs to
     * an artificial player, inclusive of the neutral player.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<GameObject> isAi() {
        return UnitOperations::isAi;
    }

    /**
     * Returns a condition that determines if a given {@link GameObject} belongs to
     * the user operating the current device.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<GameObject> isCorporeal() {
        return UnitOperations::isCorporeal;
    }

    /**
     * Returns a condition that determines if a given {@link GameObject} belongs to
     * the corporeal player, the user, or to a {@link Player#isControllable()
     * controllable player}.
     */
    public static Predicate<GameObject> isControllable() {
        return UnitOperations::isControllable;
    }

    /**
     * Returns a condition that determines if a given {@link GameObject} belongs to
     * the neutral user, a special AI player that owns resources and critters.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<GameObject> isNeutral() {
        return UnitOperations::isNeutral;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * building.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<GameObject> isBuilding() {
        return UnitOperations::isBuilding;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * combatant.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<GameObject> isCombatant() {
        return UnitOperations::isCombatant;
    }

    public static Predicate<GameObject> isNonCombatant() {
        return not(isCombatant());
    }

    public static Predicate<GameObject> isAttacker() {
        return UnitOperations::isAttacker;
    }

    public static Predicate<GameObject> isTransport() {
        return UnitOperations::isTransport;
    }

    /**
     * Returns a condition that determines if the given {@link GameObject} is a
     * critter.
     *
     * @return a {@link Predicate}.
     */
    public static Predicate<GameObject> isCritter() {
        return UnitOperations::isCritter;
    }

    public static Predicate<GameObject> isDepotFor(ResourceType resource) {
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

    public static Predicate<GameObject> isFoodProducer() {
        return UnitOperations::isFoodProducer;
    }

    public static Predicate<GameObject> isDestroyable() {
        return item -> item instanceof PerishableObject;
    }

    public static Predicate<GameObject> isGatherer() {
        return (item) -> item instanceof Gatherer;
    }

    public static Predicate<GameObject> isMovable() {
        return item -> item instanceof MovableObject;
    }

    public static Predicate<GameObject> isMovableOver(TerrainType capability) {
        return item -> {
            if (item instanceof MovableObject) {
                MovableObject movable = (MovableObject)item;
                return capability.equals(movable.getMovementCapability());
            }
            return false;
        };
    }

    public static Predicate<GameObject> isCastingSpell(Spell spell) {
        return item -> UnitOperations.isCastingSpell(item, spell);
    }

    public static Predicate<GameObject> isResource() {
        return item -> item instanceof Resource;
    }

    public static Predicate<GameObject> isSelected() {
        return (item) -> {
            if (item instanceof SelectableObject) {
                SelectableObject selectable = (SelectableObject)item;
                return selectable.getSelected();
            }
            return false;
        };
    }

    public static Predicate<GameObject> isSelectable() {
        return (item) -> item instanceof SelectableObject;
    }

    public static Predicate<GameObject> hasResources(ResourceType type) {
        return (item) -> {
            ResourceContainer container = (ResourceContainer)item;
            return container.getResource(type) > 0;
        };
    }

    public static Predicate<GameObject> hasPathTo(MovableObject source) {
        return destination -> hasPath(source, destination);
    }

    public static Predicate<GameObject> isConstructing() {
        return UnitOperations::isConstructing;
    }

//    public static Predicate<GameObject> associatedWith(GameObject withGameObject) {
//        return (item) -> item instanceof Unit && ((Unit)item).getAssociatedItem() == withGameObject;
//    }
//
//    public static Predicate<GameObject> associatedWith(Identifier type) {
//        return associatedWith(hasType(type));
//    }
//
//    public static Predicate<GameObject> associatedWith(Predicate<GameObject> condition) {
//        return (item) -> {
//            if (item instanceof Unit) {
//                Unit unit = (Unit)item;
//                GameObject associated = unit.getAssociatedItem();
//                return condition.test(associated);
//            }
//            return false;
//        };
//    }
}
