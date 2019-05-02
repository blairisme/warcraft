/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.UserInput;

import java.util.Collections;
import java.util.List;

public class IosInput implements DeviceInput
{
    @Override
    public void install() {
        //Install input listener
    }

    @Override
    public List<UserInput> readInput() {
        return Collections.emptyList();
    }
}
