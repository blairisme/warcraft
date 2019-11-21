/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.evilbird.engine.device.DeviceControls;

/**
 * Provide information about the controls available on the Android platform.
 *
 * @author Blair Butterworth
 */
public class AndroidControls implements DeviceControls
{
    @Override
    public boolean supportsMenuOption() {
        return true;
    }

    @Override
    public boolean supportsMiniMap() {
        return false;
    }

    @Override
    public boolean supportsPause() {
        return true;
    }
}
