/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.warcraft.object.common.query.UnitOperations;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmLocation;
import static com.evilbird.warcraft.action.transport.TransportActions.TransportDisembark;
import static com.evilbird.warcraft.action.transport.TransportActions.TransportEmbark;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isControllable;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isMovableOver;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DisembarkButton;

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
            .whenTarget(UnitOperations::isTransport)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void disembark() {
        addAction(TransportDisembark)
            .whenTarget(DisembarkButton)
            .whenSelected(UnitOperations::isTransport)
            .appliedTo(Selected);
    }
}
