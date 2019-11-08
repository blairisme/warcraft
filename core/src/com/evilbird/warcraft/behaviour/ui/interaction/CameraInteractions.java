/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
import static com.evilbird.warcraft.item.data.camera.CameraType.Camera;
import static com.evilbird.warcraft.item.display.control.status.selection.SelectionButtonType.FocusButton;

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
