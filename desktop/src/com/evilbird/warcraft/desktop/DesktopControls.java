/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
    public boolean supportsMenuOption() {
        return false;
    }

    @Override
    public boolean supportsMiniMap() {
        return true;
    }

    @Override
    public boolean supportsPause() {
        return false;
    }
}
