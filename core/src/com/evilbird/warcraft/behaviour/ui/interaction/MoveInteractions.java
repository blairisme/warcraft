/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.layer.LayerType;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.function.Predicate;

import static com.evilbird.engine.object.utility.GameObjectPredicates.hasType;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmLocation;
import static com.evilbird.warcraft.action.move.MoveActions.MoveCancel;
import static com.evilbird.warcraft.action.move.MoveActions.MoveToLocation;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Air;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.capability.TerrainType.ShallowWater;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Water;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isControllable;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isMovableOver;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StopButton;
import static com.evilbird.warcraft.object.layer.LayerType.Map;
import static com.evilbird.warcraft.object.layer.LayerType.OpaqueFogSection;
import static com.evilbird.warcraft.object.layer.LayerType.Sea;
import static com.evilbird.warcraft.object.layer.LayerType.Shore;
import static com.evilbird.warcraft.object.unit.UnitType.CircleOfPower;
import static com.evilbird.warcraft.object.unit.UnitType.OilPatch;

/**
 * Defines user interactions that result in moving game objects.
 *
 * @author Blair Butterworth
 */
public class MoveInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public MoveInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        moveOverLand();
        moveThroughWater();
        moveThroughShallows();
        moveThroughAir();
        moveCancel();
    }

    private void moveOverLand() {
        addAction(ConfirmLocation, MoveToLocation)
            .whenSelected(isControllable().and(isMovableOver(Land)))
            .whenTarget(hasType(Map, Shore, OpaqueFogSection, CircleOfPower))
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void moveThroughWater() {
        addAction(ConfirmLocation, MoveToLocation)
            .whenSelected(isControllable().and(isMovableOver(Water)))
            .whenTarget(hasType(Sea, OilPatch, OpaqueFogSection))
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void moveThroughShallows() {
        addAction(ConfirmLocation, MoveToLocation)
            .whenSelected(isControllable().and(isMovableOver(ShallowWater)))
            .whenTarget(hasType(Sea, Shore, OilPatch, OpaqueFogSection))
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void moveThroughAir() {
        addAction(ConfirmLocation, MoveToLocation)
            .whenSelected(isControllable().and(isMovableOver(Air)))
            .whenTarget(hasType(LayerType.values()))
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void moveCancel() {
        addAction(MoveCancel)
            .whenSelected(isControllable().and(isMovable()))
            .whenTarget(StopButton)
            .withAction(MoveToLocation)
            .appliedTo(Selected);
    }

    private static Predicate<GameObject> isMovable() {
        return item -> item instanceof MovableObject;
    }
}
