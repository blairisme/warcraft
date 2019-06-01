/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
