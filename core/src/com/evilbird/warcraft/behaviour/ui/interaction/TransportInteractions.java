/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmLocation;
import static com.evilbird.warcraft.action.transport.TransportActions.TransportDisembark;
import static com.evilbird.warcraft.action.transport.TransportActions.TransportEmbark;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Land;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isControllable;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isMovableOver;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isTransport;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.DisembarkButton;

/**
 * Defines user interactions that result in the transportation of game objects
 * by a transport game object.
 *
 * @author Blair Butterworth
 */
public class TransportInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public TransportInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        embark();
        disembark();
    }

    private void embark() {
        addAction(TransportEmbark, ConfirmLocation)
            .whenSelected(both(isControllable(), isMovableOver(Land)))
            .whenTarget(isTransport())
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void disembark() {
        addAction(TransportDisembark)
            .whenTarget(DisembarkButton)
            .whenSelected(isTransport())
            .appliedTo(Selected);
    }
}
