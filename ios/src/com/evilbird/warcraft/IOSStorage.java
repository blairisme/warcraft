/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.evilbird.engine.device.DeviceStorage;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class IOSStorage implements DeviceStorage
{
    @Override
    public List<String> list(String path) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Reader read(String path) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Writer write(String path) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(String path) throws IOException {
        throw new UnsupportedOperationException();
    }
}
