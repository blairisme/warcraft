/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.device;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

/**
 * Implementors of this interface provide methods that facilitate access to a
 * storage area on the device.
 *
 * @author Blair Butterworth
 */
public interface DeviceStorage
{
    List<String> list(String path) throws IOException;

    Reader read(String path) throws IOException;

    Writer write(String path) throws IOException;

    void remove(String path) throws IOException;
}
