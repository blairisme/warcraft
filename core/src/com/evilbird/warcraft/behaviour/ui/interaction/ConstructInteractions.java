/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.construct.ConstructActions;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.object.selector.SelectorType;
import com.evilbird.warcraft.object.selector.building.BuildingSelector;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.gatherer.Gatherer;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.function.BiFunction;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.object.utility.GameObjectPredicates.withType;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isBuilding;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isConstructing;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.CancelButton;

/**
 * Defines user interactions that result in construction of building game
 * objects.
 *
 * @author Blair Butterworth
 */
public class ConstructInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public ConstructInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        beginConstruction();
        cancelConstruction();
        constructUpgrades();
    }

    private void beginConstruction() {
        for (SelectorType buildingSelector: SelectorType.buildingSelectors()) {
            UnitType building = buildingSelector.getBuilding();
            ConstructActions action = ConstructActions.forProduct(building);
            beginConstruction(buildingSelector, action, building);
        }
    }

    private void beginConstruction(SelectorType selector, ConstructActions action, UnitType building) {
        addAction(action)
            .whenTarget(both(withType(selector), this::isSelectorClear))
            .whenSelected(UnitOperations::isGatherer)
            .appliedTo(Selected);

        addAction(action)
            .whenSelected(both(UnitOperations::isGatherer, isConstructing(building)))
            .whenTarget(both(withType(building), UnitOperations::isUnderConstruction))
            .appliedTo(Selected);
    }

    private void cancelConstruction() {
        for (ConstructActions constructAction: ConstructActions.values()) {
            if (constructAction.isCancel()) {
                cancelConstruction(constructAction, constructAction.getProduct());
            }
        }
    }

    private void cancelConstruction(ActionIdentifier cancelAction, UnitType building) {
        addAction(cancelAction)
            .whenTarget(CancelButton)
            .whenSelected(both(withType(building), UnitOperations::isUnderConstruction))
            .appliedTo(selectedItem(), getConstructor());

        addAction(cancelAction)
            .whenTarget(CancelButton)
            .whenSelected(both(UnitOperations::isGatherer, isConstructing(building)))
            .appliedTo(getConstruction(), selectedItem());
    }

    private void constructUpgrades() {
        for (ActionButtonType button: ActionButtonType.values()) {
            if (button.isBuildingUpgradeButton()) {
                UnitType building = button.getBuildProduct();
                ConstructActions action = ConstructActions.forProduct(building);

                addAction(action)
                    .whenTarget(button)
                    .whenSelected(isBuilding())
                    .appliedTo(Selected);
            }
        }
    }

    private boolean isSelectorClear(GameObject gameObject) {
        if (gameObject instanceof BuildingSelector) {
            BuildingSelector selector = (BuildingSelector)gameObject;
            return selector.isClear();
        }
        return false;
    }

    private BiFunction<GameObject, GameObject, GameObject> getConstruction() {
        return (target, selected) -> ((Gatherer)selected).getConstruction();
    }

    private BiFunction<GameObject, GameObject, GameObject> getConstructor() {
        return (target, selected) -> ((Building)selected).getConstructor();
    }
}
