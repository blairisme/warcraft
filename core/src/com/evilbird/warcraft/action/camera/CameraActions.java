/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.camera;

import com.evilbird.engine.action.ActionIdentifier;

/**
 * Defines options of specifying camera actions.
 *
 * @author Blair Butterworth
 */
public enum CameraActions implements ActionIdentifier
{
    /**
     * An identifier for the {@link PanAction} action.
     */
    Pan,

    /**
     * An identifier for the {@link ZoomAction} action.
     */
    Zoom,

    /**
     * An identifier for the {@link FocusAction} action.
     */
    Focus
}
