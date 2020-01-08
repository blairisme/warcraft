/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.device.UserInputType;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.device.UserInputType.Drag;
import static com.evilbird.warcraft.action.camera.CameraActions.Focus;
import static com.evilbird.warcraft.action.camera.CameraActions.Pan;
import static com.evilbird.warcraft.action.camera.CameraActions.Zoom;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Target;
import static com.evilbird.warcraft.object.data.camera.CameraType.Camera;
import static com.evilbird.warcraft.object.display.components.status.selection.SelectionButtonType.FocusButton;

/**
 * Defines user interactions that result in manipulation of the game camera.
 *
 * @author Blair Butterworth
 */
public class CameraInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public CameraInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        pan();
        zoom();
        focus();
    }

    private void pan() {
        addAction(Pan)
            .forInput(Drag)
            .whenTarget(Camera)
            .appliedTo(Target);
    }

    private void zoom() {
        addAction(Zoom)
            .forInput(UserInputType.Zoom)
            .whenTarget(Camera)
            .appliedTo(Target);
    }

    private void focus() {
        addAction(Focus)
            .whenTarget(FocusButton)
            .appliedTo(targetParentItem(), selectedItem());
    }
}
