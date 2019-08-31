/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.evilbird.engine.device.DeviceControls;

/**
 * Provide information about the controls available on the desktop platform.
 *
 * @author Blair Butterworth
 */
public class DesktopControls implements DeviceControls
{
    @Override
    public boolean supportMenuOption() {
        return false;
    }

    @Override
    public boolean supportsPause() {
        return false;
    }
}
