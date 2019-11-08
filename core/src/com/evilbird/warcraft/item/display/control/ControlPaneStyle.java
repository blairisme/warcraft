/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control;

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
