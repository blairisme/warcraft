/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.device;

import java.util.List;

/**
 * Implementors of this interface read user input to the application.
 *
 * @author Blair Butterworth
 */
public interface DeviceInput
{
    void startMonitoring();

    void stopMonitoring();

    List<UserInput> getInput();
}
