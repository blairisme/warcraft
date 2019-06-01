/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.UserInput;

import java.util.Collections;
import java.util.List;

/**
 * Reads user input to the iOS application, generating the appropriate
 * {@link UserInput} events.
 *
 * @author Blair Butterworth
 */
public class IosInput implements DeviceInput
{
    @Override
    public void startMonitoring() {
    }

    @Override
    public void stopMonitoring() {
    }

    @Override
    public List<UserInput> getInput() {
        return Collections.emptyList();
    }
}
