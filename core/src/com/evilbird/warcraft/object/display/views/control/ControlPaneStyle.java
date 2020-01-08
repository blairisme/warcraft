/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.views.control;

/**
 * Defines the visual presentation of a {@link ControlPane}.
 *
 * @author Blair Butterworth
 */
public class ControlPaneStyle
{
    boolean showMenuButton;
    boolean showMiniMap;
    boolean showStatus;
    boolean showActions;

    public ControlPaneStyle() {
        this.showMenuButton = true;
        this.showMiniMap = true;
        this.showStatus = true;
        this.showActions = true;
    }
}
