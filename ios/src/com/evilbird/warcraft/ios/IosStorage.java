/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.ios;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.evilbird.engine.device.DeviceStorage;

/**
 * Provides access to device persistence.
 *
 * @author Blair Butterworth
 */
public class IosStorage implements DeviceStorage
{
    private FileHandleResolver resolver;

    public IosStorage() {
        this(new LocalFileHandleResolver());
    }

    public IosStorage(FileHandleResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public FileHandleResolver getFileHandleResolver() {
        return resolver;
    }
}
